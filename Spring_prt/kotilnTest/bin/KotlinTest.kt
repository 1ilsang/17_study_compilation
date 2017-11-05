fun main(args: Array<String>) {
    when_expression()
}

fun when_expression(){
    val code = 55
    when(code){
        44-> println("UK")
        in 1..999 -> println("Unk cnty code")
		55-> println("hi")
//        else ->{
//            println("inval c")
//        }
    }
}
