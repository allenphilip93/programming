import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalaryCalculator {
	double basicPay,HRA,specialAllowance,employerPF,employerESI,EDLI;
	//fixed components
	int bonus = 1500;
	int employerLWF = 20;
	
	public void calculateSalaryComponents(double annualCTC) {
		double monthlyCTC = annualCTC/12.;
		basicPay = monthlyCTC/2.;//50%
		
		HRA = basicPay/2.;
		HRA =  (double) BigDecimal.valueOf(HRA).setScale(2, RoundingMode.HALF_UP).doubleValue();//rounding to two decimal places
		
		EDLI = Math.min((0.0115)*basicPay, 172.5);
        EDLI =  (double) BigDecimal.valueOf(EDLI).setScale(2, RoundingMode.HALF_UP).doubleValue();
        
        double partialESI = 0.0475 * (basicPay + HRA);
        // partialESI =  (double) BigDecimal.valueOf(partialESI).setScale(2, RoundingMode.HALF_UP).doubleValue();
		
		//The remaining three components have dependencies on each other
		//rearranging the relationship amidst them gives the formula for specialAllowance
		double remainingAmoutAvailable = monthlyCTC - (basicPay+HRA+EDLI+bonus+employerLWF);
		remainingAmoutAvailable = (double) BigDecimal.valueOf(remainingAmoutAvailable).setScale(2, RoundingMode.HALF_UP).doubleValue();

		// specialAllowance = (double) ((remainingAmoutAvailable - (0.1675*basicPay+0.0475*HRA))/1.1675);
		// specialAllowance = (double) BigDecimal.valueOf(specialAllowance).setScale(2, RoundingMode.HALF_UP).doubleValue();
		
		// employerPF = (double) (0.12*(basicPay+specialAllowance));
		// employerPF = (double) BigDecimal.valueOf(employerPF).setScale(2, RoundingMode.HALF_UP).doubleValue();

		// employerESI = (double) (0.0475*(basicPay+HRA+specialAllowance));
        // employerESI = (double) BigDecimal.valueOf(employerESI).setScale(2, RoundingMode.HALF_UP).doubleValue();
        
        // Assume employee PF capped off
        employerPF = 1800.;
        specialAllowance = (remainingAmoutAvailable - partialESI - employerPF)/1.0475;

        System.out.println("SPECIAL ALLOW : " + specialAllowance + " | REMAINING : " + remainingAmoutAvailable);

        if (0.12 * (basicPay + HRA) > 1800. || specialAllowance < 0) {
            // Assume employee PF not capped off
            double partialEmployerPF = 0.12 * basicPay;
            specialAllowance = (remainingAmoutAvailable - partialESI - partialEmployerPF)/1.1675;
            employerPF = 0.12 * specialAllowance + partialEmployerPF;
            System.out.println("SPECIAL ALLOW : " + specialAllowance + " | REMAINING : " + remainingAmoutAvailable);
        }

        employerPF = (double) BigDecimal.valueOf(employerPF).setScale(2, RoundingMode.HALF_UP).doubleValue();
        employerESI = 0.0475 * (basicPay + HRA + specialAllowance);
        employerESI =  (double) BigDecimal.valueOf(employerESI).setScale(2, RoundingMode.HALF_UP).doubleValue();

        specialAllowance = remainingAmoutAvailable - employerESI - employerPF;
        // specialAllowance = (double) BigDecimal.valueOf(specialAllowance).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	//driver function
	public static void main(String[] args) {
		SalaryCalculator salaryCalculator = new SalaryCalculator();
		
		//for testing the output
		salaryCalculator.calculateSalaryComponents((double) 169998.00);
		System.out.println("1) Basic pay: "+salaryCalculator.basicPay);
		System.out.println("2) HRA: "+salaryCalculator.HRA);
		System.out.println("3) SpecialAllowance: "+salaryCalculator.specialAllowance);//
		System.out.println("4) EmployerPF: "+salaryCalculator.employerPF);//
		System.out.println("5) EmployerESI: "+salaryCalculator.employerESI);//
		System.out.println("6) EDLI: "+salaryCalculator.EDLI);
		System.out.println("7) Bonus: "+salaryCalculator.bonus);
		System.out.println("8) EmployerLWF: "+salaryCalculator.employerLWF);
				
	}
}