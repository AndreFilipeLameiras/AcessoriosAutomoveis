package pt.ipg.acessoriosautomoveis

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BdInstrumentedTest {

    private fun getAppContext(): Context =
        InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun apagaBaseDados(){
        getAppContext().deleteDatabase(BdAcessoriosOpenHelper.NOME_BASE_DADOS)

    }

    @Test
    fun consegueAbrirBaseDados(){
        val openHelper = BdAcessoriosOpenHelper(getAppContext())
        val bd = openHelper.readableDatabase
        assert(bd.isOpen)

    }

    @Test
    fun consegueInserirAcessorioInterior(){
        val openHelper = BdAcessoriosOpenHelper(getAppContext())
        val bd = openHelper.writableDatabase

        val acessorio = AcessInter("radio", "sony", "autorradio")
        val id = TabelaAcesInter(bd).insere(acessorio.toContentValues())
        assertNotEquals(-1,id)
    }
}