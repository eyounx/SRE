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
 * class Eff_Ackley
 * @author Yi-Qi Hu
 * @time 2016.4.24
 * Ackley function with epsilon effective dimension
 */

package ObjectiveFunctions;

import Components.Dimension;
import Components.Instance;
import Tools.EuclideanProjection;
import Tools.Matrix;

public class Eff_Ackley implements Task{
	
	private int eff_size;
	private Dimension dim; //dimension
	private double opt[];  //the center of sphere
	
	public Eff_Ackley(int eff_s, int size){
		eff_size = eff_s;
		dim = new Dimension();
		dim.setSize(size);
		dim.setDimension(-1, 1, true);
		opt = new double[size];
		for(int i=0; i<size; i++){
			opt[i] = 0.2;
		}
	}

	@Override
	public double getValue(double[] x) {
		// TODO Auto-generated method stub
		double[] v;
        double squaresum = 0;
        double cossum = 0;
        double sum = 0;
        
        v = new double[eff_size];
        for(int i=0; i<eff_size; i++){
        	v[i] = x[i] - opt[i];
        }
        for(int i=0; i<eff_size; i++){
            squaresum += v[i]*v[i];
            cossum += Math.cos(Math.PI*2*v[i]);
        }
        squaresum /= (double)v.length;
        cossum /= (double)v.length;
        double v1 = -0.2*Math.sqrt(squaresum);
        sum = sum += -20*Math.exp(v1)-Math.exp(cossum)+20+Math.E;
        
        for(int i=eff_size; i<dim.getSize(); i++){
        	sum += (1.0/(dim.getSize()-eff_size)*(x[i] - opt[i])*(x[i] - opt[i]));
        }
        
        return sum;

	}

	@Override
	public Dimension getDim() {
		// TODO Auto-generated method stub
		return dim;
	}

}
