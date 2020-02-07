import org.junit.Test;

import java.util.List;

public class TestBasic {

    @Test
    public void testString(){
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        l2.next.next.next = new ListNode(8);

        ListNode dummy = new ListNode(0);
        int carry = 0;
        ListNode prev = dummy;
        while(l1 != null && l2 != null){
            int val = (l1.val + l2.val + carry)%10;
            System.out.println("Val: "+val);
            int nc = (l1.val + l2.val + carry)/10;
            System.out.println("nc: "+nc);
            carry = nc;
            ListNode node = new ListNode(val);
            prev.next = node;
            prev = node;

            //increment
            l1 = l1.next;
            l2 = l2.next;
        }
        // use longer list
        while(l1 != null){
            ListNode node = new ListNode(l1.val);
            prev.next = node;
            prev = node;
            //increment
            l1 = l1.next;
        }
        // use longer list
        while(l2 != null){
            ListNode node = new ListNode(l2.val);
            prev.next = node;
            prev = node;
            //increment
            l2 = l2.next;
        }
        //System.out.println(dummy.next.val+"->"+dummy.next.next.val+"->"+dummy.next.next.next.val);
        //print list
        ListNode tmp = dummy.next;
        while(tmp != null){
            System.out.print(tmp.val+"->");
            tmp = tmp.next;
        }
        System.out.println("null");
    }


    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
}
