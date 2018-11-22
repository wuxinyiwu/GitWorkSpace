package prox;

public class CarProx extends SUVCar{
	private SUVCar suvcar;
	public CarProx(){
		super();
		suvcar=new SUVCar();
	}
	@Override
	public void run() {
		befor();
		super.run();
		after();
	}
	private void after() {
		System.out.println("after running");
	}
	private void befor() {
		System.out.println("befor running");
	}
}
