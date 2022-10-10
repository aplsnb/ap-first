package org.bang.ap.first.app.lesson

import java.util.ArrayList

// kotlin扩展extensions
fun main() {
    Jump().test()
    println("Jump().doubleJump(): ${Jump().doubleJump()}")

    val list = mutableListOf(1, 2, 3, 4)
    list.swap(0, 2)
    list.forEach {
        println("$it")
    }

    val android = "android"
    val lastChar = android.lastChar
    println("last: $lastChar")

    Jump.print("伴生对象的扩展")

    // apply内置扩展函数
    testApply()
}

class Jump {
    fun test() {}

    companion object
}

// 扩展方法的定义，就是在方法的前面加上类前缀
fun Jump.doubleJump(): String {
    return "doubleJump"
}

// 泛型扩展
fun <T> MutableList<T>.swap(index: Int, index2: Int) {
    val temp = this[index]
    this[index] = this[index2]
    this[index2] = temp
}

// 属性扩展
val String.lastChar: Char get() = this[length - 1]

// 伴生对象扩展
fun Jump.Companion.print(str: String) {
    println(str)
}

// let扩展函数，类后面加上?代表参数可能为空，使用的时候注意判空
fun testLet(str: String?) {
//    str.let {
//        val str2 = "android"
//        println(str2 + it)
//    }
//    println(str2) 访问不到

    // 判断用法，当str为空，则不会触发闭包里面的逻辑，避免为null的操作
    str?.let {
        val str2 = "android"
        println(str2 + it)
    }
}

// run扩展函数
// 返回值为最后一行或者指定return的表达式，run函数中可以直接访问实例的公有属性和方法
fun testRun(jump: Jump) {
    jump.run {
        // jump.test()
        test()
        println("11111")
        return@run "222"
    }
}

// apply扩展函数
// apply函数的作用是：调用某对象的apply函数，在函数范围内，可以任意调用该对象的任意方法，并返回该对象
// 从结构上来看apply函数和run函数很像，唯一不同点就是它们各自返回值不同，run函数是以闭包形式返回最后一行代码的值
fun testApply() {
    ArrayList<String>().apply {
        add("111")
        add("222")
    }.run {
        for (s in this) {
            println("apply: $s")
        }
    }
}
