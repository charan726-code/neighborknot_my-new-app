package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.model.BarterListing
import com.example.data.model.KnotExchange
import com.example.data.model.UserProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [BarterListing::class, KnotExchange::class, UserProfile::class],
    version = 1,
    exportSchema = false
)
abstract class NeighborknotDatabase : RoomDatabase() {
    abstract fun barterDao(): BarterDao

    companion object {
        @Volatile
        private var INSTANCE: NeighborknotDatabase? = null

        fun getInstance(context: Context): NeighborknotDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NeighborknotDatabase::class.java,
                    "neighborknot_db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            getInstance(context).seedInitialData()
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }
    }

    suspend fun seedInitialData() {
        val dao = barterDao()
        
        // Seed initial listings
        val sampleListings = listOf(
            BarterListing(
                title = "Active Sourdough Starter & Proofing Basket",
                type = "OFFERING",
                category = "Homegrown & Pantry",
                hasText = "100g 4-year active rye sourdough starter in weck jar + woven cane banneton",
                seeksText = "Fresh culinary herbs (rosemary, French thyme) or backyard honeycomb",
                neighborName = "Clara Jensen",
                neighborHandle = "@clara_bakes",
                neighborBio = "Baker on 4th Ave · Zero-waste advocate · 38 successful trades",
                neighborKarma = 184,
                distanceMiles = 0.4,
                timeAgoText = "0.4 mi away · 2 hrs ago",
                localDrawableName = "img_sourdough_jar",
                status = "ACTIVE"
            ),
            BarterListing(
                title = "Need Electric Orbital Sander (2 Hours)",
                type = "REQUESTING",
                category = "Tools & Workshop",
                hasText = "Can bake 2 loaves of fresh woodfired rosemary focaccia + peach chutney jar",
                seeksText = "Random orbital sander + 120/220 grit sanding discs for restored bench",
                neighborName = "Julian Mercer",
                neighborHandle = "@julian_m",
                neighborBio = "Woodworker & urban gardener on Maple St · 100% reciprocal exchange",
                neighborKarma = 168,
                distanceMiles = 0.2,
                timeAgoText = "0.2 mi away · 1 hr ago",
                localDrawableName = "img_tools_harvest",
                status = "ACTIVE"
            ),
            BarterListing(
                title = "Heirloom Brandywine Tomatoes & Sweet Basil",
                type = "OFFERING",
                category = "Homegrown & Pantry",
                hasText = "5 lbs vine-ripened organic heirloom tomatoes & 2 fragrant bunches Italian basil",
                seeksText = "Handmade ceramic coffee mug or masonry drill bit set loan for an afternoon",
                neighborName = "Marcus Vance",
                neighborHandle = "@marcus_urbanfarm",
                neighborBio = "Permaculturist & seed saver · Maple district community garden organizer",
                neighborKarma = 215,
                distanceMiles = 0.8,
                timeAgoText = "0.8 mi away · 3 hrs ago",
                localDrawableName = "img_tools_harvest",
                status = "ACTIVE"
            ),
            BarterListing(
                title = "Bicycle Gear Tuning & Chain Hot-Waxing",
                type = "OFFERING",
                category = "Skill Share",
                hasText = "Complete drivetrain ultrasonic degrease, molten paraffin wax & derailleur indexing",
                seeksText = "Houseplant cuttings (Monstera or Pothos) or homemade ginger kombucha scoby",
                neighborName = "Devon Lee",
                neighborHandle = "@devon_pedals",
                neighborBio = "Bike mechanic · Daily cycle commuter · Tool library volunteer",
                neighborKarma = 132,
                distanceMiles = 1.1,
                timeAgoText = "1.1 mi away · 5 hrs ago",
                status = "ACTIVE"
            ),
            BarterListing(
                title = "Need Heavy-Duty Wheelbarrow for Saturday",
                type = "REQUESTING",
                category = "Tools & Workshop",
                hasText = "Will return washed & clean + carton of 12 fresh pasture-raised farm eggs",
                seeksText = "Sturdy steel single or dual wheelbarrow for moving compost mulch bed",
                neighborName = "Elena Morales",
                neighborHandle = "@elena_green",
                neighborBio = "Landscape designer & beekeeper · 12th St",
                neighborKarma = 94,
                distanceMiles = 1.5,
                timeAgoText = "1.5 mi away · Yesterday",
                status = "ACTIVE"
            ),
            BarterListing(
                title = "Hand-thrown Ribbed Ceramic Planters",
                type = "OFFERING",
                category = "Craft & Ceramics",
                hasText = "Two terracotta glazed planters (6-inch & 8-inch) with drainage saucers",
                seeksText = "Half-day lawn aerator loan or knife & axe waterstone sharpening session",
                neighborName = "Soren & Maya",
                neighborHandle = "@earth_clay",
                neighborBio = "Studio potters · Local clay & pit-fired stoneware enthusiasts",
                neighborKarma = 276,
                distanceMiles = 2.3,
                timeAgoText = "2.3 mi away · Yesterday",
                status = "ACTIVE"
            )
        )
        dao.insertAllListings(sampleListings)

        // Seed initial knot exchanges for "The Knot" Barter Ledger
        val sampleExchanges = listOf(
            KnotExchange(
                listingId = 2,
                myItem = "2 Rosemary Focaccia Loaves + Peach Chutney",
                neighborName = "Marcus Vance",
                neighborHandle = "@marcus_urbanfarm",
                neighborItem = "Electric Orbital Sander + 3 Sanding Pads",
                distanceText = "0.6 mi · Elm Street Bench",
                status = "TRADE_ACCEPTED",
                meetingLocation = "Elm Street Community Park Pavilion",
                mutualKarma = 20,
                notes = "Agreed to trade Saturday at 10 AM. Sander returned Sunday evening."
            ),
            KnotExchange(
                listingId = 1,
                myItem = "Fresh Italian Basil + Backyard Garden Mint",
                neighborName = "Clara Jensen",
                neighborHandle = "@clara_bakes",
                neighborItem = "100g Rye Sourdough Starter & Cane Banneton",
                distanceText = "0.4 mi · 4th Ave Front Porch",
                status = "KNOT_FINALIZED",
                meetingLocation = "Clara's Porch Bread Box (Pick-up)",
                mutualKarma = 15,
                notes = "Exchange completed smoothly! Beautiful fragrant basil and active starter."
            ),
            KnotExchange(
                listingId = 4,
                myItem = "Vintage Japanese Pruning Shears Loan",
                neighborName = "Devon Lee",
                neighborHandle = "@devon_pedals",
                neighborItem = "Full Commuter Bike Drivetrain Tune & Chain Wax",
                distanceText = "1.1 mi · Bicycle Co-op Porch",
                status = "OFFER_SENT",
                meetingLocation = "Community Tool Shed on Birch St",
                mutualKarma = 25,
                notes = "Proposed barter: Devon tunes the touring bike, in exchange for 3-day shears loan."
            )
        )
        dao.insertAllExchanges(sampleExchanges)

        // Seed initial user profile
        val user = UserProfile(
            id = 1,
            name = "Julian Mercer",
            handle = "@julian_m",
            neighborhood = "Maple & 4th Urban Commons",
            bio = "Woodworker, home baker & urban gardener · Believer in tokenless community reciprocity.",
            karmaScore = 168,
            knotsTiedCount = 19,
            zeroWasteKg = 34.5,
            activeTradesCount = 3
        )
        dao.insertProfile(user)
    }
}
