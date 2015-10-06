package cn.bzhang.dota2.abaddon.parser;

import cn.bzhang.dota2.abaddon.constants.Statics;
import cn.bzhang.dota2.abaddon.constants.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import skadistats.clarity.decoder.Util;
import skadistats.clarity.model.Entity;
import skadistats.clarity.model.FieldPath;
import skadistats.clarity.processor.entities.Entities;
import skadistats.clarity.processor.entities.UsesEntities;
import skadistats.clarity.processor.reader.OnTickStart;
import skadistats.clarity.processor.runner.Context;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.TreeMap;

public class ReplayData {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private int numPlayers = 10;

    private int gameTime = -1;
    private int gameStartTime = -1;
    private int gameEndTime = -1;

    private float nextInterval = 0;
    private float INTERVAL = 1;

    private int[] indPlayers = new int[numPlayers];
    private int[] heroId = new int[numPlayers];
    private int[] teamSlot = new int[numPlayers];
    private long[] steamId = new long[numPlayers];
    private String[] playerNames = new String[numPlayers];
    private FieldPath[] fpHeroHandle = new FieldPath[numPlayers];

//    private TreeMap<Integer, Float[]>[] heroPositions = (TreeMap<Integer, Float[]>[])new Object[numPlayers];
    private TreeMap<Integer, Integer[]>[] heroPositions = (TreeMap<Integer, Integer[]>[]) Array.newInstance(TreeMap.class, numPlayers);

    private boolean indPlayersInit = false;
    private boolean miscInit = false;

    public ReplayData() {
        for (int i = 0; i < numPlayers; i++) {
            heroPositions[i] = new TreeMap<>();
        }
    }

    @UsesEntities
    @OnTickStart
    public void onTickStart(Context ctx, boolean synthetic) {
        Entity gamerulesProxy = ctx.getProcessor(Entities.class).getByDtName("CDOTAGamerulesProxy");
        Entity playerResource = ctx.getProcessor(Entities.class).getByDtName("CDOTA_PlayerResource");

        if (gamerulesProxy != null) {
            gameTime = Math.round(gamerulesProxy.getProperty("m_pGameRules.m_fGameTime"));
            if (gameStartTime < 1) {
                gameStartTime = Math.round(gamerulesProxy.getProperty("m_pGameRules.m_flGameStartTime"));
            }
            if (gameEndTime < 1) {
                gameEndTime = Math.round(gamerulesProxy.getProperty("m_pGameRules.m_flGameEndTime"));
            }
        }

        if (playerResource != null) {
            if (gameTime >= nextInterval) {
                if (!indPlayersInit) {
                    int added = 0;
                    int i = 0;
                    while (added < numPlayers) {
                        int playerTeam = playerResource.getProperty("m_vecPlayerData.%i.m_iPlayerTeam".replace("%i",
                                Util.arrayIdxToString(i)));
                        if (playerTeam == Team.RADIANT.getId() || playerTeam == Team.DIRE.getId()) {
                            indPlayers[added] = i;
                            added++;
                        }
                        i++;
                    }
                    indPlayersInit = true;
                }

                if (indPlayersInit) {
                    for (int i = 0; i < numPlayers; i++) {
                        heroId[i] = playerResource.getProperty("m_vecPlayerTeamData.%i.m_nSelectedHeroID"
                                .replace("%i", Util.arrayIdxToString(indPlayers[i])));
                    }

                    if (!miscInit) {
                        for (int i = 0; i < numPlayers; i++) {
                            steamId[i] = playerResource.getProperty("m_vecPlayerData.%i.m_iPlayerSteamID".replace
                                    ("%i", Util.arrayIdxToString(indPlayers[i])));
                            teamSlot[i] = playerResource.getProperty("m_vecPlayerTeamData.%i.m_iTeamSlot".replace
                                    ("%i", Util.arrayIdxToString(indPlayers[i])));
                            Object val = playerResource.getProperty("m_vecPlayerData.%i.m_iszPlayerName".replace
                                    ("%i", Util.arrayIdxToString(indPlayers[i])));
                            try {
                                playerNames[i] = new String(val.toString().getBytes("ISO-8859-1"));
                            } catch (UnsupportedEncodingException e) {
//                                System.out.println(e);
                                playerNames[i] = val.toString();
                            }
                            fpHeroHandle[i] = playerResource.getDtClass().getFieldPathForName(("m_vecPlayerTeamData" +
                                    ".%i" +
                                    ".m_hSelectedHero").replace("%i", Util.arrayIdxToString(indPlayers[i])));
                        }
                        miscInit = true;
                    }
                }

                for (int i = 0; i < numPlayers; i++) {
                    int handle = playerResource.getPropertyForFieldPath(fpHeroHandle[i]);
                    Entity e = ctx.getProcessor(Entities.class).getByHandle(handle);

                    if (e != null) {
                        Integer[] coord = coordFromCell(e);
//                        log.info("{}", Arrays.toString(coord));
                        heroPositions[i].put(gameTime, coord);
//                        log.info("{} {}", i, Arrays.toString(heroPositions[i].get(gameTime)));
                    }
                }
                nextInterval += INTERVAL;
            }
        }
    }

    private static Integer[] coordFromCell(Entity e) {
        Integer[] coord = new Integer[2];

        int x = e.getProperty("CBodyComponent.m_cellX");
        int y = e.getProperty("CBodyComponent.m_cellY");

        float xoff = e.getProperty("CBodyComponent.m_vecX");
        float yoff = e.getProperty("CBodyComponent.m_vecY");

        int x1 = (int)((x * 128 + xoff - 8192) / 16384.0 * Statics.MINIMAP_WIDTH);
        int y1 = (int)((24576 - 128 * y - yoff) / 16384.0 * Statics.MINIMAP_HEIGHT);

        coord[0] = x1;
        coord[1] = y1;

        return coord;
    }

    public int[] getHeroId() {
        return heroId;
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public int[] getIndPlayers() {
        return indPlayers;
    }

    public long[] getSteamId() {

        return steamId;
    }

    public int getGameStartTime() {
        return gameStartTime;
    }

    public int getGameEndTime() {
        return gameEndTime;
    }

    public TreeMap<Integer, Integer[]>[] getHeroPositions() {
        return heroPositions;
    }
}
