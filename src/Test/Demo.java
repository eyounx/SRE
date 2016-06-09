/* This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Copyright (C) 2015 Nanjing University, Nanjing, China
 */

/**
 * Class Demo
 * @author Yi-Qi Hu
 * @time 2016.4.24
 * In this class, an example will be presented to show how to use SRE-framework
 */

package Test;

import Methods.*;
import Components.*;
import ObjectiveFunctions.Eff_Ackley;
import ObjectiveFunctions.Eff_Sphere;
import ObjectiveFunctions.Task;
import SRE_Framework.SRE_Opt;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//parameters of task
		int eff_size = 10;
		int all_size = 10000;
		//parameters of sre
		int sub_size = 10;
		int step_size = 5;
		double variance = 1.0/sub_size;
		
		double sub_l = -1;
		double sub_u = 1;
		double[][] sub_region = new double[sub_size][2];
		for(int i=0; i<sub_size; i++){
			sub_region[i][0] = sub_l;
			sub_region[i][1] = sub_u;
		}
		
		//parameters of racos
		int samplesize = 20;
		int budget = 10000;
		int positivenum = 1;
		double probability = 0.99;
		int uncertainbit = 1;
			
		Task t = new Eff_Ackley(eff_size, all_size);
		RACOS method = new RACOS();
		
		//racos parameters setting
		method.setBudget(budget);
		method.setPositiveNum(positivenum);
		method.setSampleSize(samplesize);
		method.setRandProbability(probability);
		method.setUncertainBits(uncertainbit);
		Dimension ori_dim = t.getDim();
		
		SRE_Opt sre_racos = new SRE_Opt();
		
		//sre parameters setting
		sre_racos.setParameters(t, method, sub_size, ori_dim, variance, step_size, sub_region);
		
		//call sre optimization
		sre_racos.SRE_Optimization();
		
		Instance optimal;
		
		//get optimal
		optimal = sre_racos.getOptimal();
		
		System.out.println("best fitness is "+optimal.getValue());
		
		
		
		
		

	}

}
