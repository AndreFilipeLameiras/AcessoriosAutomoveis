package pt.ipg.acessoriosautomoveis

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQuery
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns



class TabelaCarro (db: SQLiteDatabase): TabelaDB(db, NOME_TABELA){
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, $CAMPO_MARCA TEXT NOT NULL, $CAMPO_COR TEXT NOT NULL, $CAMPO_FK_ACESSINTER INTEGER NOT NULL, $CAMPO_FK_ACESSEXTER INTEGER NOT NULL, FOREIGN KEY ($CAMPO_FK_ACESSINTER) REFERENCES ${TabelaAcesInter.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT, FOREIGN KEY ($CAMPO_FK_ACESSEXTER) REFERENCES ${TabelaAcesExter.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT )")
    }

    override fun consulta(
        colunas: Array<String>,
        selecao: String?,
        argsSelecao: Array<String>?,
        groupby: String?,
        having: String?,
        orderby: String?
    ): Cursor {
        val sql = SQLiteQueryBuilder()
        sql.tables = "$NOME_TABELA INNER JOIN ${TabelaAcesInter.NOME_TABELA} ON ${TabelaAcesInter.CAMPO_ID}=$CAMPO_FK_ACESSINTER"
        sql.tables = "$NOME_TABELA INNER JOIN ${TabelaAcesExter.NOME_TABELA} ON ${TabelaAcesExter.CAMPO_ID}=$CAMPO_FK_ACESSEXTER"

        return sql.query(db, colunas, selecao, argsSelecao, groupby, having, orderby)
    }

    companion object{

        const val NOME_TABELA = "carro"

        const val CAMPO_ID = "$NOME_TABELA.${BaseColumns._ID}"
        const val CAMPO_MARCA = "marca"
        const val CAMPO_COR = "cor"

        const val CAMPO_FK_ACESSINTER = "id_acinter"
        const val CAMPO_NOME_ACESSINTER = TabelaAcesInter.CAMPO_NOME


        const val CAMPO_FK_ACESSEXTER = "id_acexter"
        const val CAMPO_NOME_ACESSEXTER = TabelaAcesExter.CAMPO_NOME

        val CAMPOS = arrayOf(BaseColumns._ID, CAMPO_MARCA, CAMPO_COR, CAMPO_FK_ACESSINTER, CAMPO_FK_ACESSEXTER)
    }

}