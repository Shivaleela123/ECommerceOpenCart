package testCases;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Test1 obj= new Test1();
		int val = obj.dis();
		System.out.println(val);
		System.out.println(obj.dis());		
	}

}

class Test1 {

   private int i = 100;
	
   public int dis() {
	   return this.i;
   }
	
}