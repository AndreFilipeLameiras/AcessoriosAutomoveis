package pt.ipg.acessoriosautomoveis

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

class CarroContentProvider : ContentProvider(){
    private var bdOpenHelper: BdAcessoriosOpenHelper? = null


    override fun onCreate(): Boolean {
        bdOpenHelper = BdAcessoriosOpenHelper(context)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val bd = bdOpenHelper!!.readableDatabase

        val endereco = uriMatcher().match(uri)
        val tabela = when (endereco){
            URI_ACESSINTER, URI_ACESSINTER_ID -> TabelaAcesInter(bd)
            URI_ACESSEXTER, URI_ACESSEXTER_ID -> TabelaAcesExter(bd)
            URI_CARROS, URI_CARROS_ID -> TabelaCarro(bd)
            else -> null
        }

        val id = uri.lastPathSegment

        val (selecao, argsSel) = when (endereco){
            URI_ACESSINTER_ID, URI_CARROS_ID -> Pair("${BaseColumns._ID}=?", arrayOf(id))
            URI_ACESSEXTER_ID, URI_CARROS_ID -> Pair("${BaseColumns._ID}=?", arrayOf(id))
            else -> Pair(selection, selectionArgs)
        }

        return tabela?.consulta(
            projection as Array<String>,
            selecao,
            argsSel as Array<String>?,
            null,
            null,
            sortOrder
        )
    }

    override fun getType(uri: Uri): String? {
        val endereco = uriMatcher().match(uri)

        return when (endereco){
            URI_ACESSINTER -> "vnd.android.cursor.dir/$ACESSINTER"
            URI_ACESSINTER_ID -> "vnd.android.cursor.item/$ACESSINTER"
            URI_ACESSEXTER -> "vnd.android.cursor.dir/$ACESSEXTER"
            URI_ACESSEXTER_ID -> "vnd.android.cursor.item/$ACESSEXTER"
            URI_CARROS -> "vnd.android.cursor.dir/$CARROS"
            URI_CARROS_ID -> "vnd.android.cursor.item/$CARROS"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val bd = bdOpenHelper!!.writableDatabase

        val endereco = uriMatcher().match(uri)
        val tabela = when (endereco){
            URI_ACESSINTER -> TabelaAcesInter(bd)
            URI_ACESSEXTER -> TabelaAcesExter(bd)
            URI_CARROS -> TabelaCarro(bd)
            else -> return null
        }

        val id = tabela.insere(values!!)
        if(id == -1L){
            return null
        }

        return Uri.withAppendedPath(uri, id.toString())

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val bd = bdOpenHelper!!.writableDatabase

        val endereco = uriMatcher().match(uri)
        val tabela = when (endereco){
            URI_ACESSINTER -> TabelaAcesInter(bd)
            URI_ACESSEXTER -> TabelaAcesExter(bd)
            URI_CARROS -> TabelaCarro(bd)
            else -> return 0
        }

        val id = uri.lastPathSegment!!
        return tabela.elimina("${BaseColumns._ID}=?", arrayOf(id))
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val bd = bdOpenHelper!!.writableDatabase

        val endereco = uriMatcher().match(uri)
        val tabela = when (endereco){
            URI_ACESSINTER -> TabelaAcesInter(bd)
            URI_ACESSEXTER -> TabelaAcesExter(bd)
            URI_CARROS -> TabelaCarro(bd)
            else -> return 0
        }

        val id = uri.lastPathSegment!!
        return tabela.altera(values!!, "${BaseColumns._ID}=?", arrayOf(id))
    }



    companion object{
        private const val AUTORIDADE = "pt.ipg.acessoriosautomoveis"

        private const val ACESSINTER = "acessorios de interior"
        private const val ACESSEXTER = "acessorios de exterior"
        private const val CARROS = "carros"

        private const val URI_ACESSINTER = 100
        private const val URI_ACESSINTER_ID = 101
        private const val URI_ACESSEXTER = 200
        private const val URI_ACESSEXTER_ID = 201
        private const val URI_CARROS = 300
        private const val URI_CARROS_ID = 301

        private val ENDERECO_BASE = Uri.parse("content://$AUTORIDADE")

        val ENDERECO_ACESSiNTER = Uri.withAppendedPath(ENDERECO_BASE, ACESSINTER)
        val ENDERECO_ACESSEXTER = Uri.withAppendedPath(ENDERECO_BASE, ACESSEXTER)
        val ENDERECO_CARROS = Uri.withAppendedPath(ENDERECO_BASE, CARROS)

        fun uriMatcher() = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTORIDADE, ACESSINTER, URI_ACESSINTER)
            addURI(AUTORIDADE, "$ACESSINTER/#", URI_ACESSINTER_ID)
            addURI(AUTORIDADE, ACESSEXTER, URI_ACESSEXTER)
            addURI(AUTORIDADE, "$ACESSEXTER/#", URI_ACESSEXTER_ID)
            addURI(AUTORIDADE, CARROS, URI_CARROS)
            addURI(AUTORIDADE, "$CARROS/#", URI_CARROS_ID)
        }
    }
}