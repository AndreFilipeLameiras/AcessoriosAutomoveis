package pt.ipg.acessoriosautomoveis

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

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
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }



    companion object{
        private const val AUTORIDADE = "pt.ipg.carros"

        const val ACESSINTER = "acessorios de interior"
        const val ACESSEXTER = "acessorios de exterior"
        const val CARROS = "carros"

        private const val URI_ACESSINTER = 100
        private const val URI_ACESSEXTER = 200
        private const val URI_CARROS = 300

        fun uriMatcher() = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTORIDADE, ACESSINTER, URI_ACESSINTER)
            addURI(AUTORIDADE, ACESSEXTER, URI_ACESSEXTER)
            addURI(AUTORIDADE, CARROS, URI_CARROS)
        }
    }
}