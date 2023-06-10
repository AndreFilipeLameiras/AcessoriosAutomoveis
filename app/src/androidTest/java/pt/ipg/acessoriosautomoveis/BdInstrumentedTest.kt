package pt.ipg.acessoriosautomoveis

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
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


    @Test
    fun consegueLerAceInter(){
        val bd = getWritableDatabasse()

        val acessIntPunho = AcessInter("Punho de mudanças", "BC", "classico curto costura")
        assertNotEquals(-1, acessIntPunho)

        val acessIntTapete = AcessInter("tapete", "goodyear", "tapetes de borracha e alcatifa")
        assertEquals(-1, acessIntTapete)

        val tabelaAcessInter = TabelaAcesInter(bd)

        val cursor = tabelaAcessInter.consulta(
            TabelaAcesInter.CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(acessIntTapete.id.toString()),
            null,
            null,
            null
        )

        assert(cursor.moveToNext())

        val acessIntBD = AcessInter.fromCursor(cursor)

        assertEquals(acessIntTapete, acessIntBD)

        val cursorTodosAcessInter = tabelaAcessInter.consulta(
            TabelaAcesInter.CAMPOS,
            null, null, null, null,
            TabelaAcesInter.CAMPO_NOME

        )

        assert(cursorTodosAcessInter.count > 1)
    }

    @Test
    fun consegueLerAceExter(){
        val bd = getWritableDatabasse()

        val acessExtCorrente = AcessExter("corrente", "goodyear", "cinzenta")
        assertNotEquals(-1, acessExtCorrente)

        val acessExtTampoes = AcessExter("tampoes", "bridgtone", "verde")
        assertEquals(-1, acessExtTampoes)

        val tabelaAcessExter = TabelaAcesExter(bd)

        val cursor = tabelaAcessExter.consulta(
            TabelaAcesExter.CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(acessExtTampoes.id.toString()),
            null,
            null,
            null
        )

        assert(cursor.moveToNext())

        val acessExtBD = AcessExter.fromCursor(cursor)

        assertEquals(acessExtTampoes, acessExtBD)

        val cursorTodosAcessExter = tabelaAcessExter.consulta(
            TabelaAcesExter.CAMPOS,
            null, null, null, null,
            TabelaAcesExter.CAMPO_NOME

        )

        assert(cursorTodosAcessExter.count > 1)
    }

    @Test
    fun consegueLerCarros(){
        val bd = getWritableDatabasse()

        val acessInt = AcessInter("protector", "wrc", "protector de cinto")
        insereAcessInt(bd, acessInt)

        val acessExt = AcessExter("tornilhos", "locks", "prateados")
        insereAcessExt(bd, acessExt)

        val carro1 = Carro("Nissan", "vermelho", acessInt.id, acessExt.id)
        insereCarro(bd, carro1)

        val tabelaCarro = TabelaCarro(bd)

        val cursor = tabelaCarro.consulta(
            TabelaCarro.CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(carro1.id.toString()),
            null,
            null,
            null
        )

        assert(cursor.moveToNext())

        val carroBD = Carro.fromCursor(cursor)

        assertEquals(carro1, carroBD)

        val cursorTodosCarros = tabelaCarro.consulta(
            TabelaCarro.CAMPOS,
            null, null,null,null,
            TabelaCarro.CAMPO_MARCA
        )

        assert(cursorTodosCarros.count > 1)
    }

}