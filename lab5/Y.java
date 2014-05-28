public class Y extends X implements Z{
	private int y;
	public Y(){
       y = 1;
	}

	public static void main(String[] args){
		X[] xa = new X[2];
		Y[] ya = new Y[2];
		xa = ya;
		ya = (Y[])xa;
		Y y = new Y();
		X x = new X();
		System.out.println(((X)y).forZ(1));
		System.out.println(((Y)x).forZ(1));
		System.out.println(Z.c);
     
	}

	public String forZ(){
		return "y";
	}

}

interface Z{

	public String forZ(int b);
	public static final int c = 1;
}