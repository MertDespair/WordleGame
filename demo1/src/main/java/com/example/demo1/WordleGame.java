package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

public class WordleGame extends Application {

    private static final int MAX_TRIES = 5; // Max number of tries allowed
    private static String SECRET_WORD = "HELLO"; // Secret word to guess


    private static final String[] words = {
                    "apple", "apron", "azure", "bacon", "baked", "baker", "baldy", "basic", "beach", "beard",
                    "beast", "beats", "began", "begin", "being", "belly", "bench", "bible", "biker", "birds",
                    "birth", "black", "blade", "blame", "blank", "blast", "blaze", "blend", "bless", "blind",
                    "blink", "bliss", "block", "blood", "bloom", "blues", "bluff", "board", "boast", "bogus",
                    "bogie", "bogey", "boils", "bonds", "bonus", "booze", "bored", "borne", "botch", "bound",
                    "bouts", "boxed", "boxes", "brags", "brain", "brake", "brave", "bravo", "brawn", "bread",
                    "break", "breed", "briar", "brick", "bride", "brief", "bring", "brisk", "broad", "broil",
                    "broke", "brook", "broom", "brown", "brute", "build", "built", "bulge", "bulks", "bulky",
                    "bulls", "bully", "bumps", "bumpy", "buoys", "burns", "burnt", "burrs", "burst", "buses",
                    "busts", "butch", "butts", "cabin", "cable", "cadet", "caged", "cakes", "calls", "camel",
                    "campy", "canal", "candy", "caned", "canes", "canny", "canon", "cards", "cargo", "carol",
                    "carry", "carts", "carve", "cases", "caste", "catch", "cater", "cause", "caves", "cease",
                    "celeb", "cello", "cents", "chafe", "chain", "chair", "chalk", "chant", "chaos", "charm",
                    "chase", "cheap", "cheek", "cheer", "chefs", "chews", "chick", "child", "chill", "chimp",
                    "china", "chips", "chive", "chock", "choir", "choke", "chomp", "chose", "chunk", "churn",
                    "cider", "cigar", "cited", "cites", "civil", "claim", "clamp", "clams", "clang", "clash",
                    "clasp", "class", "claws", "clays", "clean", "clear", "cleat", "clefs", "clerk", "click",
                    "cliff", "climb", "cling", "cloak", "clock", "clone", "close", "cloth", "cloud", "clout",
                    "clove", "clown", "clubs", "cluck", "clump", "clung", "coach", "coast", "cocoa", "coder",
                    "codes", "coils", "coins", "colas", "colon", "color", "comas", "combo", "combs", "comic",
                    "comma", "comps", "conch", "cones", "conga", "cooky", "cools", "coons", "coops", "coped",
                    "copes", "cords", "cored", "corps", "costs", "couch", "could", "count", "court", "cover",
                    "covet", "cowed", "cower", "crabs", "cramp", "crane", "crank", "crash", "crate", "crawl",
                    "craze", "crazy", "creak", "cream", "creep", "crept", "crest", "cribs", "crick", "crime",
                    "crisp", "croak", "crook", "cross", "croup", "crowd", "crown", "crude", "cruel", "crumb",
                    "crush", "crust", "crypt", "cubes", "cuffs", "cuing", "culls", "cults", "cupid", "cured",
                    "cures", "curly", "curse", "curve", "cushy", "cuter", "cycle", "cynic", "daily", "dairy",
                    "dance", "dandy", "dared", "dares", "darks", "dated", "dates", "datum", "daunt", "dazed",
                    "deals", "dealt", "deans", "dears", "death", "debit", "debug", "debut", "decaf", "decay",
                    "decoy", "decry", "deeds", "deems", "deeps", "defer", "defog", "deify", "deign",
                    "deism", "deist", "deity", "delay", "delft", "delis", "dells", "delta", "delve", "demon",
                    "demur", "denim", "dense", "depot", "depth", "derby", "deter", "detox", "deuce", "devil",
                    "dhoti", "dials", "diary", "dicey", "dicks", "dicky", "dicta", "didnt", "diked", "dikes",
                    "dills", "dilly", "dimly", "dines", "dingo", "dings", "dinky", "diode", "dirge", "disco",
                    "dishy", "disks", "ditch", "ditto", "divan", "divas", "dived", "dives", "divot", "dizzy",
                    "dobro", "docks", "dodge", "dodgy", "doing", "doled", "doles", "dolls", "dolly", "dolor",
                    "dolts", "doors", "doped", "dopes", "dopey", "dorks", "dorky", "dorms", "dosed", "doses",
                    "dotty", "doubt", "dough", "douse", "dover", "dowdy", "downs", "downy", "dowry", "dozed",
                    "dozen", "dozes", "draft", "drags", "drain", "drake", "drama", "drank", "drape", "drawn",
                    "draws", "dread", "dream", "drear", "dress", "drier", "drift", "drill", "drink", "drips",
                    "drive", "droll", "drone", "drool", "droop", "drops", "drove", "drown", "druid", "drums",
                    "drunk", "dryad", "dryer", "dubya", "duchy", "ducks", "ducky", "duels", "duets", "duffy",
                    "dulls", "dully", "dummy", "dumps", "dunce", "dunes", "dunks", "dusty", "dutch", "duvet",
                    "dwarf", "dweeb", "dwelt", "dying", "eager", "eagle", "early", "earth", "easel", "eaten",
                    "ebbed", "ebony", "echos", "edged", "edges", "edict", "edits", "eerie", "eight", "eject",
                    "eking", "elder", "elect", "elite", "elope", "elude", "elvis", "emacs", "email", "embed",
                    "empty", "enact", "ended", "endow", "enjoy", "enrol", "ensue", "enter", "entry", "envoy",
                    "eosin", "epoxy", "equal", "equip", "erase", "erect", "erode", "erupt", "essay", "ethic",
                    "evade", "event", "every", "evict", "evils", "exact", "exalt", "exams", "excel", "exert",
                    "exile", "exist", "exits", "expel", "extra", "exude", "exult", "exxon", "eying", "fable",
                    "faced", "faces", "facet", "facts", "faded", "fades", "fagot", "fails", "faint", "fairs",
                    "fairy", "faith", "faked", "faker", "fakes", "fancy", "fangs", "fared", "fares", "farms",
                    "fasts", "fatal", "fated", "fates", "fault", "fauna", "fawns", "faxed", "faxes", "fears",
                    "feast", "feeds", "feels", "feign", "feint", "fence", "ferry", "fetus", "fever", "fewer",
                    "fezes", "fiber", "fiche", "fifth", "fifty", "fight", "filed", "filer", "files", "fills",
                    "films", "filmy", "filth", "final", "finch", "finds", "fined", "finer", "fines", "fired",
                    "fires", "firms", "first", "firth", "fishy", "fists", "fiver", "fives", "fixed", "fixer",
                    "fixes", "fjord", "flack", "flags", "flail", "flair", "flake", "flaky", "flame", "flank",
                    "flaps", "flare", "flash", "flask", "flats", "flaws", "flays", "fleas", "fleck", "flees",
                    "fleet", "flesh", "flick", "flies", "fling", "flint", "flips", "flirt", "float", "flock",
                    "flogs", "floof", "flood", "floor", "flops", "flora", "floss", "flour", "flout", "flown",
                    "flows", "fluff", "fluid", "fluke", "flung", "flunk", "flush", "flute", "flyer", "flyby",
                    "foamy", "focal", "focus", "foggy", "foils", "foist", "folds", "folio", "folks", "folly",
                    "fonts", "foods", "fools", "footy", "foray", "force", "forge", "forgo", "forks", "forms",
                    "forte", "forth", "forts", "forty", "forum", "fouls", "found", "fount", "fours", "fowls",
                    "foxes", "foyer", "frail", "frame", "franc", "frank", "fraud", "frays", "freak", "freed",
                    "freer", "frees", "frets", "friar", "fried", "fries", "frill", "frisk", "fritz", "frizz",
                    "frogs", "frond", "front", "frost", "froth", "frown", "froze", "fruit", "frump", "fryer",
                    "fudge", "fuels", "fugal", "fugue", "fully", "fumes", "funds", "fungi", "funny", "furls",
                    "furor", "furry", "furze", "fused", "fuses", "fussy", "futon", "fuzzy", "gabby", "gable",
                    "gaffs", "gaily", "gains", "galas", "gales", "galls", "galop", "gamed", "games", "gamma",
                    "gangs", "gaols", "gaped", "gapes", "garbs", "gases", "gassy", "gated", "gates", "gator",
                    "gaudy", "gauge", "gavel", "gawky", "gayer", "gazed", "gazes", "gears", "gecko", "geeks",
                    "geese", "gelds", "gelid", "genes", "genie", "genoa", "genre", "genus", "gents", "genua",
                    "genus", "germs", "getup", "ghats", "ghost", "ghoul", "giant", "giddy", "gifts", "gilds",
                    "gills", "gilts", "gimpy", "girth", "given", "giver", "gives", "glade", "glads", "glare",
                    "glass", "glaze", "gleam", "glean", "glees", "glens", "glide", "glint", "gloat", "globs",
                    "gloom", "glory", "gloss", "glove", "glows", "glued", "glues", "gluts", "gnarl", "gnash",
                    "gnats", "gnaws", "gnome", "goads", "goals", "goats", "going", "goldy", "golly", "gonad",
                    "gongs", "gonna", "goods", "goofs", "goofy", "goons", "goose", "gored", "gores", "gorge",
                    "gorse", "gouge", "gourd", "gouts", "gowns", "grabs", "grace", "grade", "graft", "grail",
                    "grain", "grams", "grand", "grape", "graph", "grasp", "grass", "grate", "grave", "gravy",
                    "grays", "graze", "great", "grebe", "greed", "greek", "green", "greet", "greys", "grids",
                    "grief", "grill", "grime", "grimy", "grind", "grins", "grips", "gripe", "grits", "groan",
                    "groom", "grope", "gross", "group", "grout", "grove", "growl", "grown", "grows", "grubs",
                    "gruel", "gruff", "grump", "grunt", "guano", "guard", "guava", "guess", "guest", "guide",
                    "guild", "guile", "guilt", "guise", "gulch", "gulfs", "gulls", "gully", "gumbo", "gummy",
                    "gunks", "gunky", "gurus", "gusty", "gutsy", "gypsy", "habit", "hacks", "hades", "haiku",
                    "hails", "hairs", "hairy", "hakes", "haled", "haler", "hales", "halls", "halos", "halts",
                    "halve", "hands", "handy", "hangs", "hanks", "happy", "hardy", "harem", "harks", "harms",
                    "harps", "harry", "harsh", "harts", "hasps", "haste", "hasty", "hatch", "hated", "hater",
                    "hates", "hauls", "haunt", "haven", "haves", "havoc", "hawks", "hayed", "hazel", "hazed",
                    "hazel", "hazer", "hazes", "heads", "heady", "heals", "heard", "hears", "heart", "heats",
                    "heave", "heavy", "hedge", "heels", "hefts", "hefty", "heirs", "hells", "helms", "helot",
                    "helps", "hemps", "hence", "henna", "herbs", "herds", "heron", "hertz", "hewed", "hewer",
                    "hexed", "hexes", "hicks", "hides", "highs", "hiked", "hiker", "hikes", "hills", "hilly",
                    "hilts", "hinds", "hints", "hippo", "hippy", "hired", "hirer", "hires", "hitch", "hived",
                    "hives", "hoagy", "hoard", "hoary", "hobby", "hocks", "hokey", "holed", "holes", "holey",
                    "holly", "holst", "holts", "homes", "honed", "honer", "hones", "honey", "honks", "honor",
                    "hooch", "hoods", "hoofs", "hooks", "hooky", "hoops", "hoots", "hoped", "hopes", "horas",
                    "horde", "horns", "horny", "horse", "horsy", "hosed", "hoses", "hosts", "hotel", "hotly",
                    "hound", "hours", "house", "hovel", "hover", "howdy", "howls", "hubby", "huffs", "huffy",
                    "huger", "hulas", "hulks", "hullo", "human", "humid", "humor", "humps", "humpy", "humus",
                    "hunch", "hunks", "hunti", "hunts", "hurls", "hurry", "hurts", "husks", "husky", "hussy",
                    "hutch", "hydra", "hydro", "hyena", "hying", "hyped", "hyper", "hypes", "hypos", "iambs",
                    "icier", "icily", "icing", "icons", "ideal", "ideas", "idiom", "idiot", "idled", "idler",
                    "idles", "idols", "igloo", "ileum", "image", "imago", "imams", "imbed", "imbue", "impel",
                    "imply", "inane", "incas", "incur", "index", "indie", "inept", "infer", "infix", "infos",
                    "infra", "ingle", "ingot", "inked", "inker", "inlay", "inlet", "inned", "inner", "input",
                    "inset", "inter", "intro", "ionic", "iotas", "irish", "irked", "irons", "irony", "isles",
                    "islam", "issue", "italy", "ivies", "ivory", "jabot", "jacks", "jaded", "jades", "jails",
                    "jakes", "jambs", "janet", "japan", "jarls", "jatos", "jaunt", "jazzy", "jeans", "jeeps",
                    "jelly", "jenny", "jerks", "jerky", "jests", "jetty", "jewel", "jibes", "jiffy", "jihad",
                    "jilts", "jimmy", "jived", "jives", "johns", "joint", "joist", "joked", "joker", "jokes",
                    "jolly", "jolts", "joust", "jowls", "jubal", "judas", "judge", "juice", "juicy", "julep",
                    "jules", "jumbo", "jumps", "jumpy", "junks", "junky", "junta", "juror", "kabob", "kafka",
                    "kalei", "kapok", "karat", "karen", "karma", "karst", "karts", "kasha", "kayak", "keats",
                    "kebob", "keels", "keens", "keeps", "kefir", "kelps", "kelpy", "kenya", "kepis", "kerbs",
                    "ketch", "keyed", "kicks", "kicky", "kiddy", "kicks", "kilns", "kilos", "kilts", "kings",
                    "kinks", "kiosk", "kirks", "kited", "kites", "kitty", "kiwis", "klieg", "knack", "knave",
                    "knead", "kneed", "kneel", "knell", "knelt", "knife", "knits", "knobs", "knock", "knoll",
                    "knots", "known", "knows", "koala", "kodak", "koels", "kongo", "kooks", "kooky", "kopek",
                    "kraal", "kraft", "kudos", "kulak", "ladle", "lager", "lairs", "laity", "lakes", "lambs",
                    "lamed", "lamer", "lamps", "lance", "lands", "lanes", "lanka", "lanky", "lapel", "lapse",
                    "larch", "lards", "large", "largo", "larks", "larva", "laser", "lasso", "lasts", "latch",
                    "later", "latex", "lathe", "laths", "latte", "lauds", "laugh", "laura", "lavas", "laved",
                    "laves", "laxer", "laxly", "layed", "layer", "layup", "lazed", "leach", "leads", "leafs",
                    "leafy", "leaks", "leaky", "leans", "leant", "leaps", "leapt", "learn", "lease", "leash",
                    "least", "leave", "ledge", "leech", "leeks", "leers", "lefts", "lefty", "legal", "leman",
                    "lemma", "lemon", "lemur", "lends", "lense", "lento", "leper", "level", "lever", "liars",
                    "libel", "libra", "libya", "licit", "lidos", "liens", "liers", "lieus", "liege", "lifts",
                    "light", "liked", "liken", "likes", "lilac", "lilts", "limas", "limbs", "limed", "limes",
                    "limit", "limns", "limos", "limps", "lined", "linen", "liner", "lines", "lingo", "lings",
                    "links", "lints", "linty", "lions", "lipid", "lippy", "liras", "lisle", "lists", "lithe",
                    "litre", "lived", "liven", "liver", "lives", "livid", "llama", "loafs", "loads", "loans",
                    "loath", "lobes", "lobed", "local", "lochs", "locks", "locus", "lodge", "lofts", "lofty",
                    "loges", "logic", "login", "logos", "loins", "lolls", "loner", "longs", "looks", "looms",
                    "loony", "loops", "loopy", "loose", "loots", "loped", "lopes", "lords", "lores", "loser",
                    "loses", "lotto", "lotus", "louis", "louse", "lousy", "louts", "loved", "lover", "loves",
                    "lowed", "lower", "lowly", "loyal", "luaus", "lucid", "lucks", "lucky", "lulls", "lulus",
                    "lumps", "lumpy", "lunar", "lunch", "lungs", "lupus", "lurch", "lured", "lures", "lurid",
                    "lurks", "lusts", "lusty", "lutes", "lying", "lymph", "lynch", "lyres", "lyric", "macaw",
                    "maced", "maces", "macho", "macro", "madam", "madly", "mafia", "magic", "magma", "maids",
                    "mails", "maims", "maine", "maize", "major", "maker", "makes", "malay", "males", "malta",
                    "malty", "mambo", "mamma", "mammy", "manes", "mange", "mango", "mange", "mango", "mangy",
                    "mania", "manic", "manly", "manor", "maple", "maqui", "mares", "marks", "marry", "marsh",
                    "marts", "masks", "mason", "masse", "massy", "match", "mated", "mater", "mates", "maths",
                    "matte", "matts", "matty", "mauls", "mauve", "maven", "mavin", "maxed", "maxes", "maxim",
                    "maybe", "mayor", "mayst", "mazes", "meals", "mealy", "means", "meant", "meany", "meats",
                    "meaty", "medal", "media", "medic", "meets", "melds", "melee", "melon", "melts", "memes",
                    "mends", "menus", "meows", "mercy", "merge", "merit", "merry", "mesas", "meson", "messy",
                    "metal", "meted", "meter", "metes", "meths", "mewed", "mewls", "mezzo", "micas", "micks",
                    "micro", "midst", "miens", "miffs", "might", "miked", "mikes", "milds", "milks", "milky",
                    "mills", "milts", "mimed", "mimes", "mimic", "mince", "mined", "miner", "mines", "minim",
                    "minis", "minks", "minor", "mints", "minty", "minus", "mired", "mires", "mirth", "misdo",
                    "miser", "missy", "mists", "misty", "miter", "mites", "mitts", "mixed", "mixer", "mixes",
                    "moans", "moats", "mocha", "mocks", "modal", "model", "modem", "modes", "mogul", "moils",
                    "moira", "moire", "moist", "molar", "molds", "moldy", "moles", "molls", "molly", "molts",
                    "momma", "mommy", "money", "monks", "monty", "mooch", "moods", "moody", "mooed", "moons",
                    "moony", "moose", "moots", "moped", "moper", "mopes", "mopey", "moral", "moray", "morel",
                    "mores", "morns", "moron", "mossy", "mosts", "motel", "motes", "motet", "moths", "mothy",
                    "motif", "motor", "motto", "moues", "mould", "moult", "mound", "mount", "mourn", "mouse",
                    "mousy", "mouth", "moved", "mover", "moves", "movie", "mowed", "mower", "moxie", "mucks",
                    "mucky", "mucus", "muddy", "muffs", "mufti", "muggy", "mulch", "mulct", "mules", "mulls",
                    "multi", "mumbo", "mummy", "mumps", "munch", "mural", "muras", "mured", "mures", "murks",
                    "murky", "musca", "musks", "musky", "mussy", "musts", "musty", "muted", "muter", "mutes",
                    "mutts", "mylar", "myths", "nabob", "nacho", "nacre", "nadir", "naggy", "nails", "naive",
                    "naked", "named", "names", "nanas", "nanny", "napes", "nappy", "narcs", "nares", "naris",
                    "narks", "nasal", "nasty", "natch", "natty", "naval", "navel", "naves", "navvy", "nazis",
                    "neaps", "nears", "neath", "necks", "needs", "needy", "negro", "neigh", "nepal", "nerds",
                    "nerve", "nervy", "nests", "netty", "neuva", "never", "newer", "newly", "newsy", "newts",
                    "nexus", "nicer", "niche", "nicks", "niece", "nifty", "nighs", "night", "nihil", "nines",
                    "ninja", "ninth", "nippy", "nisei", "niter", "nites", "nitro", "nixed", "nixes", "noble",
                    "nobly", "nodal", "noddy", "nodes", "noels", "nohow", "noise", "noisy", "nomad", "nonce",
                    "nooks", "nooky", "noons", "noose", "nopal", "nosed", "noses", "nosey", "notch", "noted",
                    "notes", "nouns", "novas", "novel", "noway", "nowis", "nubby", "nuder", "nudes", "nudge",
                    "nudie", "nuked", "nukes", "nulls", "numbs", "numen", "nurse", "nutty", "nylon", "nymph",
                    "oaken", "oakum", "oases", "oasis", "oaten", "oaths", "obese", "obeys", "obits", "objet",
                    "oboes", "occur", "ocean", "ocher", "ochre", "octal", "octet", "odder", "oddly", "odium",
                    "odors", "offal", "offer", "often", "ogled", "ogles", "ogres", "ohmic", "oiled", "oiler",
                    "okapi", "olden", "older", "oldie", "olive", "omega", "omens", "omits", "onces", "onion",
                    "onset", "oomph", "oozed", "oozes", "opals", "opens", "opera", "opine", "opium", "opted",
                    "optic", "orate", "orbed", "orbit", "orcas", "order", "organ", "oriel", "osier", "other",
                    "otter", "ought", "ounce", "ousts", "outdo", "outed", "outer", "outgo", "outre", "ovals",
                    "ovary", "ovate", "ovens", "overs", "overt", "ovoid", "ovule", "owned", "owner", "owsis",
                    "oxbow", "oxide", "oxter", "ozone", "paced", "pacer", "paces", "packs", "pacts", "paddy",
                    "padre", "padri", "paean", "pagan", "paged", "pager", "pages", "pails", "pains", "paint",
                    "pairs", "paled", "paler", "pales", "palls", "palms", "palsy", "panda", "panel", "panes",
                    "pangs", "panic", "pants", "panty", "papal", "papas", "paper", "papua", "parch", "pared",
                    "pares", "paris", "parks", "parol", "parry", "parse", "parts", "party", "pasha", "passe",
                    "pasta", "paste", "pasts", "patch", "pated", "paten", "pates", "paths", "patio", "patly",
                    "patsy", "patty", "pause", "paved", "paves", "pawed", "pawns", "payed", "payee", "payer",
                    "peace", "peach", "peaks", "peals", "pearl", "pears", "pease", "peats", "peaty", "pecan",
                    "pecks", "pedal", "peeks", "peels", "peens", "peeps", "peers", "peeve", "peggy", "peins",
                    "pekes", "pekin", "pekoe", "pelfs", "pelts", "penal", "pence", "penes", "pengo", "penni",
                    "penny", "peons", "peony", "peppy", "perch", "peril", "perks", "perky", "perms", "perry",
                    "perse", "pesky", "pesos", "pesto", "pests", "petal", "peter", "petit", "petty", "pewee",
                    "pewit", "phage", "phase", "phial", "phlox", "phone", "phony", "photo", "phots", "phpbb",
                    "phyla", "piano", "picky", "piece", "piers", "pieta", "piggy", "pigmy", "piked", "piker",
                    "pikes", "pilaf", "pilar", "pilau", "pilch", "pilea", "piled", "piles", "pills", "pilot",
                    "pimps", "pinch", "pined", "pines", "piney", "pings", "pinko", "pinks", "pinky", "pinon",
                    "pints", "pinup", "pions", "pious", "pipal", "piped", "piper", "pipes", "pipit", "pique",
                    "pitch", "piths", "pithy", "pitta", "pivot", "pixel", "pixes", "pizza", "place", "plaid",
                    "plain", "plait", "plane", "plank", "plans", "plant", "plash", "plate", "plato", "plats",
                    "platy", "plays", "plaza", "plead", "pleas", "pleat", "plebe", "plied", "plier", "plies",
                    "plink", "plods", "plonk", "plops", "plots", "plows", "ploys", "pluck", "plugs", "plumb",
                    "plume", "plump", "plums", "plumy", "plunk", "plush", "plyer", "poach", "pocks", "podgy",
                    "podia", "poems", "poesy", "poets", "point", "poise", "poked", "poker", "pokes", "pokey",
                    "polar", "poled", "poler", "poles", "polio", "polka", "polls", "polyp", "polys", "pomps",
                    "ponds", "pones", "pooch", "poofs", "poofy", "poohs", "pools", "poops", "popes", "poppa",
                    "poppy", "porch", "pored", "pores", "porgy", "porks", "porky", "porno", "porns", "ports",
                    "posed", "poser", "poses", "posit", "posse", "posts", "potty", "pouch", "poufs", "poult",
                    "pound", "pours", "pouts", "power", "poxed", "poxes", "prams", "prana", "prank", "prate",
                    "prawn", "prays", "preen", "preps", "press", "prest", "prexy", "preys", "price", "prick",
                    "pricy", "pride", "pried", "prier", "pries", "prigs", "prima", "prime", "primp", "prims",
                    "prink", "print", "prior", "prise", "prism", "privy", "prize", "prods", "profs", "profs",
                    "promo", "proms", "prone", "prong", "proof", "props", "prose", "prosy", "proud", "prove",
                    "prowl", "prows", "proxy", "prude", "prune", "psalm", "psych", "pubic", "pubis", "pucks",
                    "pudgy", "puffs", "puffy", "puked", "pukes", "pukka", "pulpy", "pulse", "pumas", "pumps",
                    "punch", "punks", "punky", "punts", "punty", "pupal", "pupil", "puppy", "puree", "purer",
                    "purge", "purls", "purrs", "purse", "pushy", "pussy", "putty", "pygmy", "pylon", "pyres",
                    "pyrex", "pyxis", "quack", "quads", "quaff", "quail", "quake", "quaky", "qualm", "quark",
                    "quart", "quash", "quasi", "quays", "queen", "queer", "quell", "query", "quest", "queue",
                    "quick", "quids", "quiet", "quill", "quilt", "quint", "quips", "quire", "quirk", "quirt",
                    "quite", "quits", "quoin", "quoit", "quota", "quote", "quoth", "rabbi", "rabid", "raced",
                    "racer", "races", "racks", "radar", "radii", "radio", "radon", "rafts", "ragas", "raged",
                    "rages", "raids", "rails", "rains", "rainy", "raise", "rajah", "raked", "rakes", "rally",
                    "rally", "ramps", "ranch", "rands", "randy", "ranee", "range", "rangy", "ranks", "rants",
                    "raped", "raper", "rapes", "rapid", "rared", "rarer", "rares", "rasps", "rated", "rater",
                    "rates", "ratio", "ratty", "raved", "ravel", "raven", "raver", "raves", "rawer", "rawly",
                    "rayed", "razed", "razor", "razed", "reals", "reams", "reaps", "rearm", "rears", "rebel",
                    "rebid", "rebus", "rebut", "recap", "recce", "recks", "recon", "recto", "recur", "redid",
                    "redly", "redox", "redux", "redye", "reeds", "reedy", "reefs", "reeks", "reeky", "reels",
                    "reest", "refer", "refit", "refix", "refry", "regal", "reges", "regal", "reges", "rehab",
                    "reify", "reign", "reins", "reist", "rekey", "relay", "relic", "reman", "remap", "remet",
                    "remix", "renal", "rends", "renew", "renin", "rents", "repay", "repel", "reply", "reran",
                    "rerun", "resaw", "resay", "reset", "resin", "resow", "rests", "retag", "retch", "retia",
                    "retie", "retry", "reuse", "revel", "revet", "revue", "rewed", "rewet", "rewon", "rheas",
                    "rheum", "rhino", "rhyme", "riced", "ricer", "rices", "ricky", "ricks", "ricin", "rider",
                    "rides", "ridge", "ridgy", "riels", "rifer", "rifle", "rifts", "right", "rigid", "rigor",
                    "riled", "riles", "rills", "rimes", "rimed", "rinds", "rings", "rinks", "rinse", "riots",
                    "riper", "ripes", "risen", "riser", "rises", "risky", "risus", "rites", "ritzy", "rival",
                    "rived", "riven", "river", "rives", "rivet", "riyal", "roach", "roads", "roams", "roans",
                    "roars", "roast", "robed", "robin", "rocks", "rocky", "rodeo", "roger", "rogue", "roils",
                    "roily", "roles", "rolls", "roman", "romeo", "romps", "roods", "roofs", "rooks", "rooky",
                    "rooms", "roomy", "roost", "roots", "roped", "roper", "ropes", "ropey", "roses", "rosin",
                    "rotas", "rotch", "rotes", "roths", "rotis", "rotor", "roues", "rouge", "rough", "round",
                    "roups", "roupy", "rouse", "roust", "route", "routs", "roved", "roven", "rover", "roves",
                    "rowan", "rowdy", "rowed", "rowel", "rower", "royal", "rubes", "rubin", "ruble", "rucks",
                    "ruddy", "ruder", "ruffs", "rugby", "ruing", "ruins", "ruled", "ruler", "rules", "rumba",
                    "rumen", "rummy", "rumor", "rumps", "runes", "rungs", "runic", "runny", "runts", "runty",
                    "rupee", "rural", "ruses", "rushy", "rusks", "rusts", "rusty", "ruths", "rutty", "rykes",
                    "saber", "sable", "sabra", "sabre", "sacks", "sadly", "safer", "safes", "sagas", "sager",
                    "sages", "sahib", "saids", "sails", "saint", "saith", "sakes", "sakis", "salad", "sales",
                    "sally", "salon", "salts", "salty", "salve", "salvo", "samba", "sammy", "sands", "sandy",
                    "saned", "saner", "sanes", "sanga", "sappy", "saran", "saree", "sarge", "saris", "sassy",
                    "sated", "sates", "satin", "sauce", "saucy", "sauna", "saute", "saved", "saver", "saves",
                    "savin", "savor", "savoy", "savvy", "sawed", "saxes", "sayer", "scabs", "scads", "scald",
                    "scale", "scalp", "scamp", "scams", "scans", "scant", "scape", "scare", "scarf", "scarp",
                    "scary", "scats", "scene", "scent", "schmo", "schwa", "scion", "scold", "scone", "scoop",
                    "scoot", "scope", "score", "scorn", "scour", "scout", "scowl", "scows", "scrag", "scram",
                    "scrap", "scree", "screw", "scrim", "scrip", "scrod", "scrub", "scuba", "scuds", "scuff",
                    "scull", "scums", "scurf", "scuta", "scute", "seals", "seams", "seamy", "sears", "seats",
                    "sects", "sedan", "seder", "sedge", "sedum", "seeds", "seedy", "seeks", "seels", "seems",
                    "seeps", "seers", "segni", "segos", "segue", "seine", "seism", "selfs", "selva", "semis",
                    "sends", "sengi", "senna", "sensa", "sense", "senti", "serry", "serum", "serve", "servo",
                    "setae", "setal", "seton", "setup", "seven", "sever", "sewed", "sewer", "sexed", "sexes",
                    "sexto", "sexts", "shack", "shade", "shads", "shady", "shaft", "shags", "shahs", "shake",
                    "shako", "shaky", "shale", "shall", "shalt", "shame", "shams", "shank", "shape", "shard",
                    "share", "shark", "sharp", "shave", "shawl", "shays", "sheaf", "shear", "sheds", "sheen",
                    "sheep", "sheer", "sheet", "sheik", "shelf", "shell", "shend", "shent", "sheol", "shewn",
                    "shews", "shied", "shiel", "shier", "shies", "shift", "shill", "shily", "shims", "shine",
                    "shins", "shiny", "ships", "shire", "shirk", "shirr", "shirt", "shish", "shist", "shits",
                    "shiva", "shive", "shivs", "shoal", "shoat", "shock", "shoed", "shoer", "shoes", "shogs",
                    "shoji", "shone", "shook", "shool", "shoos", "shoot", "shops", "shore", "shorn", "short",
                    "shote", "shots", "shout", "shove", "shown", "shows", "showy", "shred", "shrew", "shris",
                    "shrug", "shtik", "shuck", "shuln", "shuls", "shuns", "shunt", "shush", "shute", "shuts",
                    "shyer", "shyly", "sibyl", "sicks", "sided", "sides", "sidle", "siege", "sieve", "sifts",
                    "sighs", "sight", "sigil", "sigma", "signs", "sikes", "silds", "silex", "silks", "silky",
                    "sills", "silly", "silts", "silty", "simas", "simps", "since", "sines", "sinew",
                    "singh", "sings", "sinus", "sipes", "sired", "siren", "sires", "sirup", "sisal", "sises",
                    "sissy", "sitar", "sited", "sites", "situp", "situs", "siver", "sixes", "sixth", "sixty",
                    "sized", "sizer", "sizes", "skags", "skald", "skate", "skats", "skeet", "skein", "skelp",
                    "skene", "skeps", "skews", "skids", "skied", "skier", "skies", "skiff", "skill", "skimo",
                    "skimp", "skims", "skink", "skins", "skint", "skips", "skirl", "skirt", "skits", "skoal",
                    "skuas", "skulk", "skull", "skunk", "skyed", "skyey", "slabs", "slack", "slags", "slain",
                    "slake", "slams", "slang", "slant", "slaps", "slash", "slate", "slats", "slaty", "slave",
                    "slaws", "slays", "sleds", "sleek", "sleep", "sleet", "slept", "slews", "slice", "slick",
                    "slide", "slier", "slily", "slime", "slims", "slimy", "sling", "slink", "slips", "slipt",
                    "slits", "slobs", "sloes", "slogs", "sloop", "slops", "slope", "slosh", "sloth", "slots",
                    "slued", "slues", "sluff", "slugs", "slump", "slums", "slung", "slunk", "slurp", "slurs",
                    "slush", "sluts", "slyer", "slyly", "slype", "smack", "small", "smart", "smash", "smear",
                    "smell", "smelt", "smews", "smile", "smirk", "smite", "smith", "smock", "smogs", "smoke",
                    "smoky", "smolt", "smote", "smuts", "snack", "snafu", "snags", "snail", "snake", "snaky",
                    "snaps", "snare", "snarf", "snark", "snarl", "snash", "snath", "snaws", "sneak", "sneap",
                    "sneck", "sneds", "sneer", "snell", "snibs", "snick", "snide", "sniff", "snipe", "snips",
                    "snits", "snobs", "snood", "snook", "snool", "snoop", "snoot", "snore", "snort", "snots",
                    "snout", "snows", "snowy", "snubs", "snuck", "snuff", "snugs", "soaks", "soaps", "soapy",
                    "soars", "soave", "sobas", "sober", "socas", "socks", "sodas", "soddy", "sofar", "sofas",
                    "softa", "softs", "softy", "soggy", "soils", "solar", "soled", "solei", "soles", "solid",
                    "solos", "solus", "solve", "somme", "sonar", "sonde", "sones", "songs", "sonic", "sonly",
                    "sonny", "sonsy", "sooth", "soots", "sooty", "sophs", "sophy", "sopor", "soppy", "soras",
                    "sorbs", "sords", "sorel", "sorer", "sores", "sorgo", "sorns", "sorry", "sorts", "sorus",
                    "soths", "sotol", "sough", "souks", "souls", "sound", "soups", "soupy", "sours", "souse",
                    "south", "sowar", "sowed", "sower", "soyas", "soyuz", "sozin", "space", "spade", "spado",
                    "spaed", "spaer", "spahi", "spail", "spait", "spake", "spale", "spall", "spams", "spank",
                    "spans", "spare", "spark", "spars", "spasm", "spate", "spats", "spawn", "spays", "speak",
                    "spean", "spear", "speck", "specs", "speed", "speel", "speer", "speil", "speir", "spell",
                    "spelt", "spend", "spent", "sperm", "spews", "spica", "spice", "spicy", "spied", "spiel",
                    "spier", "spies", "spiff", "spike", "spiky", "spile", "spill", "spilt", "spine", "spins",
                    "spiny", "spire", "spiry", "spirt", "spiry", "spite", "spits", "spitz", "splat", "splay",
                    "split", "spode", "spoil", "spoke", "spoof", "spook", "spool", "spoon", "spoor", "spore",
                    "sport", "spots", "spout", "sprat", "spray", "spree", "sprig", "sprit", "sprue", "sprug",
                    "spuds", "spued", "spues", "spume", "spumy", "spunk", "spurn", "spurs", "spurt", "sputa",
                    "squab", "squad", "squat", "squaw", "squeg", "squib", "squid", "stabs", "stack", "staff",
                    "stage", "stags", "stagy", "staid", "staig", "stain", "stair", "stake", "stale", "stalk",
                    "stall", "stamp", "stand", "stane", "stang", "stank", "staph", "stare", "stark", "stars",
                    "start", "stash", "state", "stats", "stave", "stays", "stead", "steak", "steal", "steam",
                    "steed", "steek", "steel", "steep", "steer", "stein", "stela", "stele", "stems", "steno",
                    "steps", "stere", "stets", "stews", "stich", "stick", "stied", "sties", "stiff", "stile",
                    "still", "stilt", "stime", "stimy", "sting", "stink", "stint", "stirk", "stirp", "stirs",
                    "stoae", "stoai", "stoas", "stoat", "stobs", "stock", "stogy", "stoic", "stoke", "stole",
                    "stoma", "stomp", "stone", "stony", "stood", "stook", "stool", "stoop", "stops", "stopt",
                    "store", "stork", "storm", "story", "stoss", "stots", "stoup", "stour", "stout", "stove",
                    "stowp", "stows", "strap", "straw", "stray", "strep", "strew", "stria", "strip", "strop",
                    "strum", "strut", "stubs", "stuck", "studs", "study", "stuff", "stull", "stump", "stums",
                    "stung", "stunk", "stuns", "stunt", "stupa", "stuns", "sturt", "styed", "styes", "style",
                    "styli", "stymy", "suave", "subah", "sucks", "sucre", "sudds", "sudor", "sudsy", "suede",
                    "suers", "suets", "sugar", "sughs", "suing", "suint", "suite", "suits", "sulci", "sulfa",
                    "sulfo", "sulks", "sulky", "sully", "sulus", "sumac", "summa", "sumos", "sumps", "sunna",
                    "sunns", "sunny", "sunup", "super", "supes", "supra", "surah", "suras", "surds", "surer",
                    "surfy", "surfs", "surge", "surgy", "surly", "surra", "sushi", "sutra", "swabs", "swage",
                    "swags", "swail", "swain", "swale", "swami", "swamp", "swamy", "swank", "swans", "swaps",
                    "sward", "swarf", "swarm", "swart", "swash", "swath", "swats", "sways", "swear", "sweat",
                    "sweer", "sweet", "swell", "swept", "swift", "swigs", "swill", "swims", "swine", "swing",
                    "swink", "swipe", "swirl", "swish", "swiss", "swith", "swive", "swobs", "swoon", "swoop",
                    "swops", "sword", "swore", "sworn", "swots", "swoun", "swung", "sylph", "synch", "syncs",
                    "synod", "syphs", "syrup", "tabby", "taber", "tabes", "tabid", "tabla", "table", "taboo",
                    "tabor", "tabun", "tabus", "taces", "tacet", "tache", "tachs", "tacit", "tacks", "tacky",
                    "tacos", "tacts", "taffy", "tafia", "taiga", "tails", "tains", "taint", "tajes", "taken",
                    "taker", "takes", "talas", "talcs", "taler", "tales", "talks", "talky", "tally", "talon",
                    "taluk", "talus", "tamal", "tamed", "tamer", "tames", "tamis", "tammy", "tamps", "tango",
                    "tangs", "tangy", "tanka", "tanks", "tansy", "tanto", "tapas", "taped", "taper", "tapes",
                    "tapir", "tapis", "tardo", "tardy", "tared", "tares", "targe", "tarns", "taroc", "tarok",
                    "taros", "tarot", "tarps", "tarry", "tarsi", "tarts", "tasks", "tasse", "taste", "tasty",
                    "tatar", "tater", "tates", "tatty", "taunt", "taupe", "tauts", "tawed", "tawer", "tawie",
                    "tawny", "tawse", "taxed", "taxer", "taxes", "taxis", "taxol", "taxon", "taxus", "teach",
                    "teaks", "teals", "teams", "tears", "teary", "tease", "teats", "techs", "techy", "teens",
                    "teeny", "teeth", "teiid", "teind", "telae", "telco", "teles", "telex", "telia", "telic",
                    "tells", "telly", "teloi", "telos", "temps", "tempt", "tench", "tends", "tenet", "tenge",
                    "tenia", "tenon", "tenor", "tense", "tenth", "tents", "tepee", "tepid", "terai", "terce",
                    "teres", "terms", "terne", "terns", "terra", "terry", "terse", "tesla", "testa", "tests",
                    "testy", "tetra", "texan", "texas", "texts", "thack", "thane", "thank", "tharm", "thaws",
                    "thebe", "theca", "theft", "thegn", "thein", "their", "theme", "thens", "there", "therm",
                    "these", "theta", "thews", "thewy", "thick", "thief", "thigh", "thill", "thine", "thing",
                    "think", "thins", "thiol", "third", "thirl", "thong", "thorn", "thoro", "thorp", "those",
                    "thous", "thraw", "three", "threw", "thrip", "throb", "throe", "throw", "thrum", "thud",
                    "thugs", "thuja", "thumb", "thump", "thunk", "thurl", "thuya", "thyme", "thymi", "thymy",
                    "tiara", "tibia", "tical", "ticks", "tidal", "tided", "tides", "tidal", "tided", "tides",
                    "tiers", "tiffs", "tiger", "tight", "tigon", "tikes", "tilak", "tilde", "tiled", "tiler",
                    "tiles", "tills", "tilth", "tilts", "timed", "timer", "times", "timid", "tinct", "tinea",
                    "tined", "tines", "tinge", "tings", "tinny", "tints", "tipis", "tipsy", "tired", "tires",
                    "tirls", "tiros", "titan", "titer", "tithe", "titis", "title", "titty", "tizzy", "toads",
                    "toady", "toast", "today", "toddy", "toeas", "toffs", "toffy", "tofts", "tofus", "togae",
                    "togas", "togae", "togas", "toged", "togue", "togus", "toile", "toils", "toits", "tokay",
                    "toked", "token", "tokes", "tolan", "tolas", "toled", "toles", "tolls", "tolus", "toman",
                    "tombs", "tommy", "tonal", "tondi", "tondo", "toned", "toner", "tones", "toney", "tonga",
                    "tongs", "tonic", "tonne", "tonus", "tools", "toons", "tooth", "toots", "topaz", "toped",
                    "topee", "toper", "topes", "tophe", "tophi", "tophs", "topic", "topis", "topos", "toque",
                    "torah", "toras", "torch", "torcs", "tores", "toric", "torii", "toros", "torot", "torrs",
                    "torse", "torsi", "torsk", "torso", "torte", "torts", "torus", "total", "toted", "totem",
                    "toter", "totes", "touch", "tough", "tours", "touse", "touts", "towed", "towel", "tower",
                    "towie", "towns", "towny", "toxic", "toxin", "toyed", "toyer", "toyon", "toyos", "trace",
                    "track", "tract", "trade", "tragi", "trail", "train", "trait", "tramp", "trams", "trank",
                    "tranq", "trans", "traps", "trapt", "trash", "trass", "trave", "trawl", "trays", "tread",
                    "treat", "treed", "treen", "trees", "treks", "trend", "tress", "treys", "triac", "triad",
                    "trial", "tribe", "trice", "trick", "tried", "trier", "tries", "trigs", "trike",
                    "trill", "trims", "trine", "tripe", "trips", "trite", "trium", "troak", "troat", "trode",
                    "trois", "troke", "troll", "tromp", "trona", "trone", "troop", "trooz", "trope", "troth",
                    "trots", "trout", "trove", "trows", "troys", "truce", "truck", "trued", "truer", "trues",
                    "trugs", "truly", "trump", "trunk", "truss", "trust", "truth", "tryma", "tryst", "tsade",
                    "tsadi", "tsars", "tsuba", "tubae", "tubal", "tubas", "tubby", "tubed", "tuber", "tubes",
                    "tucks", "tufas", "tuffs", "tufts", "tufty", "tules", "tulle", "tulip", "tumid", "tummy",
                    "tumor", "tumps", "tunas", "tuned", "tuner", "tunes", "tungs", "tunic", "tunny", "tupik",
                    "tuque", "turbo", "turds", "turfs", "turfy", "turks", "turns", "turps", "tushy", "tusks",
                    "tutee", "tutor", "tutti", "tutty", "tutus", "tuxes", "tuyer", "twaes", "twain", "twang",
                    "twats", "tweak", "tweed", "tween", "tweet", "twerp", "twice", "twier", "twigs", "twill",
                    "twine", "twins", "twiny", "twirl", "twirp", "twist", "twits", "twixt", "twyer", "tyees",
                    "tyers", "tying", "tykes", "tyned", "tynes", "typal", "typed", "types", "typey", "typic",
                    "typos", "typps", "tyred", "tyres", "tyros", "tythe", "tzars", "udder", "uhlan", "ukase",
                    "ulama", "ulans", "ulcer", "ulema", "ulnae", "ulnar", "ulnas", "ulpan", "ultra", "ulvas",
                    "umami", "umbel", "umber", "umbos", "umbra", "umiac", "umiak", "umiaq", "unais", "unapt",
                    "unarm", "unary", "unaus", "unban", "unbar", "unbed", "unbid", "unbox", "uncap", "uncia",
                    "uncle", "uncos", "uncus", "uncut", "undee", "under", "undid", "undue", "unfed", "unfit",
                    "unfix", "ungot", "unhat", "unhip", "unify", "union", "unite", "units", "unity", "unlay",
                    "unled", "unlet", "unlit", "unman", "unmet", "unmew", "unmix", "unpeg", "unpen", "unpin",
                    "unrig", "unrip", "unsay", "unsee", "unset", "unsew", "unsex", "untie", "until", "unwed",
                    "unwet", "unwit", "unwon", "unzip", "upbow", "upbye", "updos", "upend", "uplit", "upped",
                    "upper", "upset", "urban", "urbia", "ureas", "ureic", "urged", "urger", "urges", "urial",
                    "urine", "ursae", "usage", "users", "usher", "using", "usnea", "usque", "usual", "usurp",
                    "usury", "uteri", "utile", "utter", "uveal", "uveas", "uvula", "vacua", "vacuo", "vagus",
                    "vails", "vairs", "vakil", "vales", "valet", "valid", "valor", "valse", "value", "valve",
                    "vamps", "vaned", "vanes", "vangs", "vapid", "vapor", "varas", "varec", "varia", "varix",
                    "varna", "varus", "varve", "vasty", "vatic", "vatus", "vault", "vaunt", "veals", "vealy",
                    "veena", "veeps", "veers", "veery", "vegan", "vegie", "veils", "veins", "veiny", "velar",
                    "velds", "veldt", "velum", "venae", "venal", "vends", "venge", "venin", "venom", "vents",
                    "venue", "verbs", "verge", "verse", "verso", "verst", "verts", "vertu", "verve", "vesta",
                    "vests", "vetch", "vexed", "vexer", "vexes", "vexil", "vials", "viand", "vibes", "vicar",
                    "viced", "vices", "vichy", "video", "viers", "views", "viewy", "vigas", "vigil", "vigor",
                    "viler", "villa", "villi", "vills", "vimen", "vinal", "vinas", "vinca", "vined", "vines",
                    "vinic", "vinos", "vinyl", "viola", "viols", "viper", "viral", "vireo", "vires", "virga",
                    "virid", "viril", "virls", "virtu", "virus", "visas", "vised", "vises", "visit", "visor",
                    "vista", "vitae", "vital", "vitta", "vivas", "vivid", "vixen", "vizir", "vizor", "vocab",
                    "vocal", "voces", "vodka", "vodou", "vogue", "voice", "voids", "voila", "voile", "volar",
                    "voled", "voles", "volta", "volte", "volti", "volts", "volva", "vomer", "vomit", "voted",
                    "voter", "votes", "vouch", "vowed", "vowel", "vower", "vroom", "vrouw", "vrows", "vuggs",
                    "vuggy", "vughs", "vulgo", "vulva", "vying", "wacke", "wacko", "wacks", "waddy", "waded",
                    "wader", "wades", "wadis", "wafer", "waffs", "wafts", "waged", "wager", "wages", "wagon",
                    "wahoo", "waifs", "wails", "wains", "wairs", "waist", "waits", "waive", "waked", "waken",
                    "waker", "wakes", "waled", "waler", "wales", "walks", "walla", "walls", "wally", "waltz",
                    "wands", "waned", "wanes", "waney", "wanly", "wants", "wards", "wared", "wares", "warks",
                    "warms", "warns", "warps", "warts", "warty", "waste", "wasts", "watap", "watch", "water",
                    "watts", "waugh", "wauks", "wauls", "waved", "waver", "waves", "wavey", "wawls", "waxed",
                    "waxen", "waxer", "waxes", "wazoo", "weaky", "weald", "weals", "weans", "wears", "weary",
                    "weave", "webby", "wedel", "wedge", "wedgy", "weeds", "weedy", "weeks", "weens", "weeny",
                    "weeps", "weepy", "weest", "weets", "wefts", "weigh", "weird", "weirs", "wekas", "welch",
                    "welds", "wells", "welsh", "welts", "wench", "wends", "wenny", "wests", "wetas", "wetly",
                    "whack", "whale", "whamo", "whams", "whang", "whaps", "wharf", "whats", "whaup", "wheal",
                    "wheat", "wheel", "wheen", "wheep", "whelk", "whelm", "whelp", "whens", "where", "whets",
                    "whews", "wheys", "which", "whids", "whiff", "whigs", "while", "whims", "whine", "whins",
                    "whiny", "whips", "whipt", "whirl", "whirr", "whirs", "whish", "whisk", "whist", "white",
                    "whits", "whity", "whizz", "whole", "whomp", "whoof", "whoop", "whops", "whore", "whorl",
                    "whort", "whose", "whoso", "whump", "wicks", "widdy", "widen", "wider", "wides", "widow",
                    "width", "wield", "wifed", "wifes", "wifty", "wigan", "wiggy", "wight", "wilco", "wilds",
                    "wiled", "wiles", "wills", "willy", "wilts", "wimps", "wimpy", "wince", "winch", "winds",
                    "windy", "wined", "wines", "winey", "wings", "wingy", "winks", "winos", "winze", "wiped",
                    "wiper", "wipes", "wired", "wirer", "wires", "wirra", "wised", "wiser", "wises", "wishy",
                    "wisps", "wispy", "wists", "witan", "witch", "wited", "witen", "wites", "withe", "withy",
                    "witty", "wived", "wiver", "wives", "wizen", "wizes", "woads", "woald", "woful", "woken",
                    "wolds", "wolfs", "woman", "wombs", "wonky", "wonts", "woods", "woody", "wooed", "wooer",
                    "woofs", "wools", "wooly", "woops", "woosh", "woozy", "words", "wordy", "works", "world",
                    "worms", "wormy", "worry", "worse", "worst", "worth", "worts", "would", "wound", "woven",
                    "wowed", "wrack", "wrapr", "wraps", "wrapt", "wrath", "wreak", "wreck", "wrens", "wrest",
                    "wrick", "wried", "wrier", "wries", "wring", "wrist", "write", "writs", "wrong", "wrote",
                    "wroth", "wrung", "wryer", "wryly", "wurst", "wyled", "wyles", "wynds", "wynns", "wyted",
                    "wytes", "xebec", "xenia", "xenic", "xenon", "xeric", "xerox", "xerus", "xylan", "xylem",
                    "xylol", "xylyl", "xysti", "xysts", "yacks", "yaffs", "yager", "yagis", "yahoo", "yaird",
                    "yamen", "yamun", "yangs", "yanks", "yapok", "yapon", "yappy", "yards", "yarer", "yarns",
                    "yauld", "yaups", "yawed", "yawls", "yawns", "yawps", "yeans", "yearn", "years", "yeast",
                    "yecch", "yechs", "yechy", "yeggs", "yelks", "yells", "yelps", "yenta", "yente", "yerba",
                    "yerks", "yeses", "yetis", "yetts", "yeuky", "yield", "yikes", "yills", "yince", "yipes",
                    "yirds", "yirrs", "yirth", "ylems", "yobbo", "yocks", "yodel", "yodhs", "yodle", "yogas",
                    "yogee", "yoghs", "yogic", "yogin", "yogis", "yoked", "yokel", "yokes", "yolks", "yolky",
                    "yonis", "yores", "young", "yourn", "yours", "youse", "youth", "yowed", "yowes", "yowie",
                    "yowls", "yperu", "yugas", "yulan", "yules", "yummy", "yupon", "yurta", "yurts",
                    "zaire", "zamia", "zanza", "zappy", "zarfs", "zarfs", "zaxes", "zeals", "zebec", "zebra",
                    "zebus", "zelko", "zelos", "zeros", "zests", "zesty", "zetas", "zibet", "zilch",
                    "zills", "zincs", "zincy", "zineb", "zings", "zingy", "zinky", "ziram", "zitis", "zizit",
                    "zlote", "zoeae", "zoeal", "zoeas", "zombi", "zonae", "zonal", "zoned", "zoner", "zones",
                    "zonic", "zonks", "zooey", "zooid", "zooks", "zooms", "zoons", "zooty", "zoril", "zoris",
                    "zowie", "zuzim" };
            private int currentTry = 0; // Current try count
    private Text[][] resultTexts = new Text[MAX_TRIES][SECRET_WORD.length()]; // Texts to show results
    private TextField inputField; // Input field for guessing
    private Button guessButton; // Button to make a guess

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Wordle Game");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);

        // Set column constraints for consistent spacing
        for (int i = 0; i < SECRET_WORD.length(); i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(20); // Each column takes 20% width
            grid.getColumnConstraints().add(column);
        }

        // Input field for guessing
        inputField = new TextField();
        inputField.setPrefWidth(100);
        grid.add(inputField, 0, MAX_TRIES + 1, SECRET_WORD.length(), 1);

        // Button to make a guess
        guessButton = new Button("Guess");
        guessButton.setOnAction(event -> checkGuess(grid));
        grid.add(guessButton, 0, MAX_TRIES + 2, SECRET_WORD.length(), 1);

        // Add initial row of squares for showing result
        addRowOfSquares(grid);

        Scene scene = new Scene(grid, 270, 400); // Adjusted scene size to accommodate larger squares and input field
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to add a row of squares for showing result
    private void addRowOfSquares(GridPane grid) {
        for (int i = 0; i < SECRET_WORD.length(); i++) {
            Rectangle square = new Rectangle(40, 40, Color.LIGHTGRAY); // Increased size to 40x40
            square.setStroke(Color.BLACK);
            square.setStrokeWidth(1.5);
            square.setArcWidth(5);
            square.setArcHeight(5);
            grid.add(square, i, currentTry);

            resultTexts[currentTry][i] = new Text("");
            resultTexts[currentTry][i].setFont(Font.font("Arial", FontWeight.BOLD, 18));
            resultTexts[currentTry][i].setStroke(Color.BLACK); // Set outline color
            resultTexts[currentTry][i].setStrokeWidth(0.3); // Set outline width
            grid.add(resultTexts[currentTry][i], i, currentTry);
            GridPane.setHalignment(resultTexts[currentTry][i], javafx.geometry.HPos.CENTER); // Center horizontally
            GridPane.setValignment(resultTexts[currentTry][i], javafx.geometry.VPos.CENTER); // Center vertically

        }
    }


    // Method to check the guess and update the result
    private void checkGuess(GridPane grid) {
        String guess = inputField.getText().toUpperCase();
        if (guess.length() != SECRET_WORD.length()) {
            return;
        }

        for (int i = 0; i < guess.length(); i++) {
            resultTexts[currentTry][i].setText(Character.toString(guess.charAt(i)));
            char guessedChar = guess.charAt(i);
            char secretChar = SECRET_WORD.charAt(i);
            if (guessedChar == secretChar) {
                resultTexts[currentTry][i].setFill(Color.GREEN);
            } else if (SECRET_WORD.contains(Character.toString(guessedChar))) {
                resultTexts[currentTry][i].setFill(Color.ORANGE);
            } else {
                resultTexts[currentTry][i].setFill(Color.RED);
            }
        }

        if (guess.equals(SECRET_WORD)) {
            displayWinMessage();
            guessButton.setDisable(true);
        } else {
            currentTry++;
            if (currentTry >= MAX_TRIES) {
                displayLoseMessage();
                guessButton.setDisable(true);
            } else {
                addRowOfSquares(grid); // Add a new row of squares
                inputField.clear();
            }
        }
    }

    // Method to display the result of the guess
    private void displayResult(String message) {
        System.out.println(message);
    }

    private void displayWinMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText(null);
        alert.setContentText("You've guessed the word: " + SECRET_WORD);
        alert.showAndWait();
    }

    // Method to display a lose message
    private void displayLoseMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Sorry, you've run out of tries. The word was: " + SECRET_WORD);
        alert.showAndWait();
    }

        public static String generateRandomWord() {
            Random random = new Random();
            int index = random.nextInt(words.length);
            return words[index];
        }

    public static void main(String[] args) {
        launch(args);
    }
}
