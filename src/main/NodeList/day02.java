//import kotlin.jvm.JvmStatic;
//
///**
// * @author kfzx-liuc02
// * @version 1.0
// * @date 2022/4/1 10:04
// * @Description
// */
//class ListNode(var data: Int) {
//    var next: ListNode? = null
//    fun addNodea(newNode: ListNode?) {
//        if (next == null) {
//            next = newNode
//        } else {
//            next!!.addNodea(newNode)
//        }
//    }
//
//}
//
//class day02链表节点想加模拟加法运算 {
//    var head: ListNode? = null
//    var tail: ListNode? = null
//    fun SumNodea(l1: ListNode?, l2: ListNode?): ListNode? {
//        var l1 = l1
//        var l2 = l2
//        var carry = 0
//        while (l1 != null || l2 != null) {
//            val n1 = l1?.data ?: 0
//            val n2 = if (l2 == null) 0 else l1!!.data
//            val sum = n1 + n2 + carry
//            if (head == null) {
//                tail = ListNode(sum % 10)
//                head = tail
//            } else {
//                tail!!.next = ListNode(sum % 10)
//                tail = tail!!.next
//            }
//            carry = sum / 10
//            if (l1 != null) {
//                l1 = l1.next
//            }
//            if (l2 != null) {
//                l2 = l2.next
//            }
//        }
//        if (carry > 0) {
//            tail!!.next = ListNode(carry)
//        }
//        return head
//    }
//
//    companion object {
//        fun SumNodeRecur(l1: ListNode?, l2: ListNode?, bit: Int): ListNode? {
//            var l1 = l1
//            var l2 = l2
//            if (l1 == null && l2 == null && bit == 0) {
//                return null
//            }
//            var `val` = bit
//            if (l1 != null) {
//                `val` = `val` + l1.data
//                l1 = l1.next
//            }
//            if (l2 != null) {
//                `val` = `val` + l2.data
//                l2 = l2.next
//            }
//            val node = ListNode(`val` % 10)
//            node.next = SumNodeRecur(l1, l2, `val` / 10)
//            return node
//        }
//
//        @JvmStatic
//        fun main(args: Array<String>) {
//            val lista = ListNode(3)
//            lista.addNodea(ListNode(9))
//            val listb = ListNode(3)
//            listb.addNodea(ListNode(9))
//            listb.addNodea(ListNode(9))
//            val listNode = SumNodeRecur(lista, listb, 0)
//            Print(listNode)
//        }
//
//        fun Print(node: ListNode?) {
//            if (node != null) {
//                println(node.data)
//                Print(node.next)
//            }
//        }
//    }
//}