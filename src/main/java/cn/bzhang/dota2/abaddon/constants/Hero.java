package cn.bzhang.dota2.abaddon.constants;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum Hero {
    ANTIMAGE(1, "Anti-Mage", "CDOTA_Unit_Hero_AntiMage"),
    AXE(2, "Axe", "CDOTA_Unit_Hero_Axe"),
    BANE(3, "Bane", "CDOTA_Unit_Hero_Bane"),
    BLOODSEEKER(4, "Bloodseeker", "CDOTA_Unit_Hero_Bloodseeker"),
    CRYSTALMAIDEN(5, "Crystal Maiden", "CDOTA_Unit_Hero_CrystalMaiden"),
    DROWRANGER(6, "Drow Ranger", "CDOTA_Unit_Hero_DrowRanger"),
    EARTHSHAKER(7, "Earthshaker", "CDOTA_Unit_Hero_Earthshaker"),
    JUGGERNAUT(8, "Juggernaut", "CDOTA_Unit_Hero_Juggernaut"),
    MIRANA(9, "Mirana", "CDOTA_Unit_Hero_Mirana"),
    MORPHLING(10, "Morphling", "CDOTA_Unit_Hero_Morphling"),
    SHADOWFIEND(11, "Shadow Fiend", "CDOTA_Unit_Hero_Nevermore"),
    PHANTOMLANCER(12, "Phantom Lancer", "CDOTA_Unit_Hero_PhantomLancer"),
    PUCK(13, "Puck", "CDOTA_Unit_Hero_Puck"),
    PUDGE(14, "Pudge", "CDOTA_Unit_Hero_Pudge"),
    RAZOR(15, "Razor", "CDOTA_Unit_Hero_Razor"),
    SANDKING(16, "Sand King", "CDOTA_Unit_Hero_SandKing"),
    STORMSPIRIT(17, "Storm Spirit", "CDOTA_Unit_Hero_StormSpirit"),
    SVEN(18, "Sven", "CDOTA_Unit_Hero_Sven"),
    TINY(19, "Tiny", "CDOTA_Unit_Hero_Tiny"),
    VENGEFULSPIRIT(20, "Vengeful Spirit", "CDOTA_Unit_Hero_VengefulSpirit"),
    WINDRANGER(21, "Windranger", "CDOTA_Unit_Hero_Windrunner"),
    ZEUS(22, "Zeus", "CDOTA_Unit_Hero_Zuus"),
    KUNKKA(23, "Kunkka", "CDOTA_Unit_Hero_Kunkka"),
    LINA(25, "Lina", "CDOTA_Unit_Hero_Lina"),
    LION(26, "Lion", "CDOTA_Unit_Hero_Lion"),
    SHADOWSHAMAN(27, "Shadow Shaman", "CDOTA_Unit_Hero_ShadowShaman"),
    SLARDAR(28, "Slardar", "CDOTA_Unit_Hero_Slardar"),
    TIDEHUNTER(29, "Tidehunter", "CDOTA_Unit_Hero_Tidehunter"),
    WITCHDOCTOR(30, "Witch Doctor", "CDOTA_Unit_Hero_WitchDoctor"),
    LICH(31, "Lich", "CDOTA_Unit_Hero_Lich"),
    RIKI(32, "Riki", "CDOTA_Unit_Hero_Riki"),
    ENIGMA(33, "Enigma", "CDOTA_Unit_Hero_Enigma"),
    TINKER(34, "Tinker", "CDOTA_Unit_Hero_Tinker"),
    SNIPER(35, "Sniper", "CDOTA_Unit_Hero_Sniper"),
    NECROPHOS(36, "Necrophos", "CDOTA_Unit_Hero_Necrolyte"),
    WARLOCK(37, "Warlock", "CDOTA_Unit_Hero_Warlock"),
    BEASTMASTER(38, "Beastmaster", "CDOTA_Unit_Hero_Beastmaster"),
    QUEENOFPAIN(39, "Queen of Pain", "CDOTA_Unit_Hero_QueenOfPain"),
    VENOMANCER(40, "Venomancer", "CDOTA_Unit_Hero_Venomancer"),
    FACELESSVOID(41, "Faceless Void", "CDOTA_Unit_Hero_FacelessVoid"),
    WRAITHKING(42, "Wraith King", "CDOTA_Unit_Hero_SkeletonKing"),
    DEATHPROPHET(43, "Death Prophet", "CDOTA_Unit_Hero_DeathProphet"),
    PHANTOMASSASSIN(44, "Phantom Assassin", "CDOTA_Unit_Hero_PhantomAssassin"),
    PUGNA(45, "Pugna", "CDOTA_Unit_Hero_Pugna"),
    TEMPLARASSASSIN(46, "Templar Assassin", "CDOTA_Unit_Hero_TemplarAssassin"),
    VIPER(47, "Viper", "CDOTA_Unit_Hero_Viper"),
    LUNA(48, "Luna", "CDOTA_Unit_Hero_Luna"),
    DRAGONKNIGHT(49, "Dragon Knight", "CDOTA_Unit_Hero_DragonKnight"),
    DAZZLE(50, "Dazzle", "CDOTA_Unit_Hero_Dazzle"),
    CLOCKWERK(51, "Clockwerk", "CDOTA_Unit_Hero_Rattletrap"),
    LESHRAC(52, "Leshrac", "CDOTA_Unit_Hero_Leshrac"),
    NATURESPROPHET(53, "Nature's Prophet", "CDOTA_Unit_Hero_Furion"),
    LIFESTEALER(54, "Life Stealer", "CDOTA_Unit_Hero_Life_Stealer"),
    DARKSEER(55, "Dark Seer", "CDOTA_Unit_Hero_DarkSeer"),
    CLINKZ(56, "Clinkz", "CDOTA_Unit_Hero_Clinkz"),
    OMNIKNIGHT(57, "Omniknight", "CDOTA_Unit_Hero_Omniknight"),
    ENCHANTRESS(58, "Enchantress", "CDOTA_Unit_Hero_Enchantress"),
    HUSKAR(59, "Huskar", "CDOTA_Unit_Hero_Huskar"),
    NIGHTSTALKER(60, "Night Stalker", "CDOTA_Unit_Hero_NightStalker"),
    BROODMOTHER(61, "Broodmother", "CDOTA_Unit_Hero_Broodmother"),
    BOUNTYHUNTER(62, "Bounty Hunter", "CDOTA_Unit_Hero_BountyHunter"),
    WEAVER(63, "Weaver", "CDOTA_Unit_Hero_Weaver"),
    JAKIRO(64, "Jakiro", "CDOTA_Unit_Hero_Jakiro"),
    BATRIDER(65, "Batrider", "CDOTA_Unit_Hero_Batrider"),
    CHEN(66, "Chen", "CDOTA_Unit_Hero_Chen"),
    SPECTRE(67, "Spectre", "CDOTA_Unit_Hero_Spectre"),
    ANCIENTAPPARITION(68, "Ancient Apparition", "CDOTA_Unit_Hero_AncientApparition"),
    DOOMBRINGER(69, "Doom Bringer", "CDOTA_Unit_Hero_DoomBringer"),
    URSA(70, "Ursa", "CDOTA_Unit_Hero_Ursa"),
    SPIRITBREAKER(71, "Spirit Breaker", "CDOTA_Unit_Hero_SpiritBreaker"),
    GYROCOPTER(72, "Gyrocopter", "CDOTA_Unit_Hero_Gyrocopter"),
    ALCHEMIST(73, "Alchemist", "CDOTA_Unit_Hero_Alchemist"),
    INVOKER(74, "Invoker", "CDOTA_Unit_Hero_Invoker"),
    SILENCER(75, "Silencer", "CDOTA_Unit_Hero_Silencer"),
    OUTWORLDDEVOURER(76, "Outworld Devourer", "CDOTA_Unit_Hero_Obsidian_Destroyer"),
    LYCAN(77, "Lycan", "CDOTA_Unit_Hero_Lycan"),
    BREWMASTER(78, "Brewmaster", "CDOTA_Unit_Hero_Brewmaster"),
    SHADOWDEMON(79, "Shadow Demon", "CDOTA_Unit_Hero_Shadow_Demon"),
    LONEDRUID(80, "Lone Druid", "CDOTA_Unit_Hero_LoneDruid"),
    CHAOSKNIGHT(81, "Chaos Knight", "CDOTA_Unit_Hero_ChaosKnight"),
    MEEPO(82, "Meepo", "CDOTA_Unit_Hero_Meepo"),
    TREANTPROTECTOR(83, "Treant Protector", "CDOTA_Unit_Hero_Treant"),
    OGREMAGI(84, "Ogre Magi", "CDOTA_Unit_Hero_Ogre_Magi"),
    UNDYING(85, "Undying", "CDOTA_Unit_Hero_Undying"),
    RUBICK(86, "Rubick", "CDOTA_Unit_Hero_Rubick"),
    DISRUPTOR(87, "Disruptor", "CDOTA_Unit_Hero_Disruptor"),
    NYXASSASSIN(88, "Nyx Assassin", "CDOTA_Unit_Hero_Nyx_Assassin"),
    NAGASIREN(89, "Naga Siren", "CDOTA_Unit_Hero_Naga_Siren"),
    KEEPEROFTHELIGHT(90, "Keeper of the Light", "CDOTA_Unit_Hero_KeeperOfTheLight"),
    IO(91, "Io", "CDOTA_Unit_Hero_Wisp"),
    VISAGE(92, "Visage", "CDOTA_Unit_Hero_Visage"),
    SLARK(93, "Slark", "CDOTA_Unit_Hero_Slark"),
    MEDUSA(94, "Medusa", "CDOTA_Unit_Hero_Medusa"),
    TROLLWARLORD(95, "Troll Warlord", "CDOTA_Unit_Hero_TrollWarlord"),
    CENTAURWARRUNNER(96, "Centaur Warrunner", "CDOTA_Unit_Hero_Centaur"),
    MAGNUS(97, "Magnus", "CDOTA_Unit_Hero_Magnataur"),
    TIMBERSAW(98, "Timbersaw", "CDOTA_Unit_Hero_Shredder"),
    BRISTLEBACK(99, "Bristleback", "CDOTA_Unit_Hero_Bristleback"),
    TUSK(100, "Tusk", "CDOTA_Unit_Hero_Tusk"),
    SKYWRATHMAGE(101, "Skywrath Mage", "CDOTA_Unit_Hero_Skywrath_Mage"),
    ABADDON(102, "Abaddon", "CDOTA_Unit_Hero_Abaddon"),
    ELDERTITAN(103, "Elder Titan", "CDOTA_Unit_Hero_Elder_Titan"),
    LEGIONCOMMANDER(104, "Legion Commander", "CDOTA_Unit_Hero_Legion_Commander"),
    TECHIES(105, "Techies", "CDOTA_Unit_Hero_Techies"),
    EMBERSPIRIT(106, "Ember Spirit", "CDOTA_Unit_Hero_EmberSpirit"),
    EARTHSPIRIT(107, "Earth Spirit", "CDOTA_Unit_Hero_EarthSpirit"),
    ABYSSALUNDERLORD(108, "Abyssal Underlord", "CDOTA_Unit_Hero_AbyssalUnderlord"),
    TERRORBLADE(109, "Terrorblade", "CDOTA_Unit_Hero_Terrorblade"),
    PHOENIX(110, "Phoenix", "CDOTA_Unit_Hero_Phoenix"),
    ORACLE(111, "Oracle", "CDOTA_Unit_Hero_Oracle");

    private final Integer id;
    private final String publicName;
    private final String internalName;

    Hero(Integer id, String publicName, String internalName) {
        this.id = id;
        this.publicName = publicName;
        this.internalName = internalName;
    }

    public Integer getId() {
        return id;
    }

    public String getPublicName() {
        return publicName;
    }

    public String getInternalName() {
        return internalName;
    }

    private static final Map<Integer, Hero> lookup = new HashMap<>();

    static {
        for (Hero h : EnumSet.allOf(Hero.class))
            lookup.put(h.getId(), h);
    }

    public static Hero get(int id) {
        return lookup.get(id);
    }
}
