package br.com.lucolimac.businesscard

import android.app.Application
import br.com.lucolimac.businesscard.data.AppDatabase
import br.com.lucolimac.businesscard.data.BusinessCardRepository

class App : Application() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { BusinessCardRepository(database.businessDao()) }
}