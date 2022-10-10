package org.bang.ap.first.app.lesson

// 条件控制
fun main() {
    println("maxOf：${maxOf(5, 10)}")

    eval(100)

    println("eval2：${eval2(100)}")

    eval3()
}

// if是有返回值的
fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun maxOf2(a: Int, b: Int): Int {
    // 三目运算
    return if (a > b) {
        a
    } else {
        b
    }
}

fun eval(number: Number) {
    if (number is Int) { // 如果是Int类型，is用来判断是否是某一种类型
        println("this is int number")
    } else if (number is Double) {
        println("this is double number")
    } else if (number is Float) {
        println("this is float number")
    } else if (number is Long) {
        println("this is long number")
    } else if (number is Byte) {
        println("this is byte number")
    } else if (number is Short) {
        println("this is short number")
    } else {
        throw IllegalArgumentException("invalid argument")
    }
}

// when同样是有返回值的
fun eval2(number: Number): String = when (number) {
    200f -> "this number is 200f"
    is Int -> "this is int number"
    is Double -> "this is double number"
    is Float -> {
        println("the number is float")
        "this is float number"
    }
    is Long -> "this is long number"
    is Byte -> "this is byte number"
    is Short -> "this is short number"
    else -> "invalid number"
}

fun eval3(): String {
    return when (val value = getValue()) { // when表达式条件是一个方法，并用value保存了返回值
        is Int -> "this is int type, value is $value".apply(::println)
        is Double -> "this is double, value is $value".apply(::println)
        is Float -> "this is float, value is $value".apply(::println)
        is String -> "this is string, value is $value".apply(::println)
        else -> "unknown type".apply(::println)
    }
}

fun getValue(): Any { // Any类似于java中的Object类型
    return 100
}
