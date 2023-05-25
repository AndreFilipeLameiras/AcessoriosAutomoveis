package pt.ipg.acessoriosautomoveis

data class Carro(
    var marca: String,
    var cor: String,
    var categoria: String,
    var idAcessInter: Int,
    var idAcessExter: Int,
    var id: Long = -1
){

}
