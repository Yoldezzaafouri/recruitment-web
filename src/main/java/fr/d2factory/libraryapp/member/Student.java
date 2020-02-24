package fr.d2factory.libraryapp.member;

public class Student extends Member {

	@Override
	public String toString() {
		return "Student [degree=" + degree + "]";
	}

	public int degree;

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public Student() {

	}

	public Student(int degree) {
		super();
		this.degree = degree;
	}

	@Override
	public float payBook(int numberOfDays) {
		// TODO Auto-generated method stub
		float toPay = 0;

		if (degree == 1) {

			System.out.println("is here");
			numberOfDays = numberOfDays - 15;
			System.out.println("nbdays "+ numberOfDays);
			if (numberOfDays > 30) {
				int x = numberOfDays - 30;
				System.out.println("x "+x);
				toPay = (float) (30 * 0.1);
				toPay = (float) (toPay + (x * 0.2));
				System.out.println("toPay ="+toPay);
			} else {
				toPay = toPay + (float) (numberOfDays * 0.10);
				System.out.println("nbDays < 30 = topay = "+toPay);
			}
			
			return toPay;

		} else {
			if (numberOfDays > 30) {
				int x = numberOfDays - 30;
				toPay = (float) (toPay + (x * 0.2) + 3.0);

				setLate(true);
				System.out.println(toPay);
			} else {
				toPay = (float) (numberOfDays * 0.1);
				setLate(false);
			}

			
		}
		return toPay;

	}

	
	

}
