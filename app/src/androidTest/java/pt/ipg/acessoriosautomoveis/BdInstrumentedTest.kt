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
        //getAppContext().deleteDatabase(BdAcessoriosOpenHelper.NOME_BASE_DADOS)

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


        val carro1 = Carro("BMW", "branco", acessInt, acessExt)
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
        insereAcessInt(bd, acessIntPunho)

        val acessIntTapete = AcessInter("tapete", "goodyear", "tapetes de borracha e alcatifa")
        insereAcessInt(bd, acessIntTapete)

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

        val acessExtTampoes = AcessExter("tampoes", "bridgestone", "prateadas")
        insereAcessExt(bd, acessExtTampoes)

        val acessExtCorrente = AcessExter("corrente", "goodYear", "cinzentas")
        insereAcessExt(bd, acessExtCorrente)

        val tabelaAcesExter = TabelaAcesExter(bd)

        val cursor = tabelaAcesExter.consulta(
            TabelaAcesExter.CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(acessExtCorrente.id.toString()),
            null,
            null,
            null
        )

        assert(cursor.moveToNext())

        val acessExtBD = AcessExter.fromCursor(cursor)

        assertEquals(acessExtCorrente, acessExtBD)

        val cursorTodosAcesExt = tabelaAcesExter.consulta(
            TabelaAcesExter.CAMPOS,
            null,null, null, null,
            TabelaAcesExter.CAMPO_DESIGNACAO
        )

        assert(cursorTodosAcesExt.count > 1)
    }

    @Test
    fun consegueLerCarros(){
        val bd = getWritableDatabasse()

        val acessInt = AcessInter("protector", "wrc", "protector de cinto")
        insereAcessInt(bd, acessInt)

        val acessExt = AcessExter("tornilhos", "locks", "prateados")
        insereAcessExt(bd, acessExt)

        val carro1 = Carro("Nissan", "vermelho", acessInt, acessExt)
        insereCarro(bd, carro1)

        val tabelaCarro = TabelaCarro(bd)

        val cursor = tabelaCarro.consulta(
            TabelaCarro.CAMPOS,
            "${TabelaCarro.CAMPO_ID}=?",
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

    @Test
    fun consegueAlterarAcesInt(){
        val bd = getWritableDatabasse()

        val acessInter = AcessInter("..", "...", "...")
        insereAcessInt(bd, acessInter)

        acessInter.nome = "colunas"

        val resgistosAlterados = TabelaAcesInter(bd).altera(
            acessInter.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(acessInter.id.toString())
        )

        assertEquals(1, resgistosAlterados)

    }

    @Test
    fun consegueAlterarAcesExt(){
        val bd = getWritableDatabasse()

        val acessExter = AcessExter("..", "..", "..")
        insereAcessExt(bd, acessExter)

        acessExter.nome = "colunas"

        val resgistosAlterados = TabelaAcesExter(bd).altera(
            acessExter.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(acessExter.id.toString())
        )

        assertEquals(1, resgistosAlterados)

    }


    @Test
    fun consegueAlterarCarros(){
        val bd = getWritableDatabasse()

        val acessIntCortinas = AcessInter ("Cortinas", "Carlinea", "cortinas laterais")
        insereAcessInt(bd, acessIntCortinas)

        val acessIntAlmofada = AcessInter ("almofada", "tortugas", "almofada cervical")
        insereAcessInt(bd, acessIntAlmofada)

        val acessExtBarras = AcessExter ("Barras", "eagle one","prateadas")
        insereAcessExt(bd, acessExtBarras)

        val acessExtGancho = AcessExter ("Gancho", "Two", "preto")
        insereAcessExt(bd, acessExtGancho)

        val carro = Carro("mercedez", "amarelo", acessIntCortinas, acessExtGancho)
        insereCarro(bd, carro)

        carro.acessInter = acessIntAlmofada
        carro.acessExter = acessExtBarras
        carro.marca = "Opel"
        carro.cor = "verde"

        val registosAlterados = TabelaCarro(bd).altera(
            carro.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(carro.id.toString())
        )

        assertEquals(1, registosAlterados)

    }


    @Test
    fun consegueApagarAcessInt(){
        val bd = getWritableDatabasse()

        val acessInt = AcessInter("","","")
        insereAcessInt(bd, acessInt)

        val registosEliminados = TabelaAcesInter(bd).elimina(
            "${BaseColumns._ID}=?",
            arrayOf(acessInt.id.toString())
        )

        assertEquals(1, registosEliminados)
    }

    @Test
    fun consegueApagarAcessExt(){
        val bd = getWritableDatabasse()

        val acessExt = AcessExter("","","")
        insereAcessExt(bd, acessExt)

        val registosEliminados = TabelaAcesExter(bd).elimina(
            "${BaseColumns._ID}=?",
            arrayOf(acessExt.id.toString())
        )

        assertEquals(1, registosEliminados)
    }


    @Test
    fun consegueApagarCarros(){
        val bd = getWritableDatabasse()

        val acessInt = AcessInter("isqueiro","bic","isqueiro")
        insereAcessInt(bd, acessInt)

        val acessExt = AcessExter("antena","autocubo","prateada")
        insereAcessExt(bd, acessExt)

        val carro = Carro("toyota","prateado",acessInt, acessExt)
        insereCarro(bd, carro)


        val registosEliminados = TabelaCarro(bd).elimina(
            "${BaseColumns._ID}=?",
            arrayOf(carro.id.toString())
        )

        assertEquals(1, registosEliminados)
    }


}