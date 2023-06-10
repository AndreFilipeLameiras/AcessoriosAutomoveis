package pt.ipg.acessoriosautomoveis

import android.content.Context
import android.database.sqlite.SQLiteDatabase
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

    private fun getWritableDatabasse(): SQLiteDatabase{
        val openHelper= BdAcessoriosOpenHelper(getAppContext())
        return openHelper.writableDatabase
    }

    @Test
    fun consegueInserirAcessorioInterior(){
        val bd = getWritableDatabasse()

        val acessInt = AcessInter("radio", "sony", "autorradio")
        insereAcessInt(bd, acessInt)

    }

    private fun insereAcessInt(
        bd: SQLiteDatabase,
        acessInt: AcessInter
    ) {
        acessInt.id = TabelaAcesInter(bd).insere(acessInt.toContentValues())
        assertNotEquals(-1, acessInt.id)

    }

    @Test
    fun consegueInserirAcessorioExterior(){
        val bd = getWritableDatabasse()

        val acessExt = AcessExter("autocolante", "corona", "preto")
        insereAcessExt(bd, acessExt)
    }

    private fun insereAcessExt(
        bd: SQLiteDatabase,
        acessExt: AcessExter
    ) {
        acessExt.id = TabelaAcesExter(bd).insere(acessExt.toContentValues())
        assertNotEquals(-1, acessExt.id)
    }

    @Test
    fun consegueInserirCarros(){
        val bd = getWritableDatabasse()

        val acessInt = AcessInter("colunas", "alpine", "colunas alta-performance")
        insereAcessInt(bd, acessInt)

        val acessExt = AcessExter("ponteira", "eipower", "cinzento")
        insereAcessExt(bd, acessExt)


        val carro1 = Carro("BMW", "branco", acessInt.id, acessInt.id)
        insereCarro(bd, carro1)


    }

    private fun insereCarro(
        bd: SQLiteDatabase,
        carro: Carro
    ) {
        carro.id = TabelaCarro(bd).insere(carro.toContentValues())
        assertNotEquals(-1, carro.id)

    }
}