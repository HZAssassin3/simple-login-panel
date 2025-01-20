import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "app_database.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL,
                password TEXT NOT NULL
            );
        """.trimIndent()
        db.execSQL(createTableQuery)

        // Example user for testing
        val insertUserQuery = """
            INSERT INTO users (username, password)
            VALUES ('mehmet', '1234'),
                   ('ahmet','3152');
        """.trimIndent()

        db.execSQL(insertUserQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun getUser(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(
            "users",
            null,
            "username =? AND password =?",
            arrayOf(username, password),
            null,
            null,
            null
        )
        val userExists = cursor.moveToFirst()
        cursor.close()
        db.close()
        return userExists
    }
}
