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
 * Sphere function
 * @author Yi-Qi Hu
 * @time 2016.4.24
 * Sphere function with epsilon effective dimension
 */
package ObjectiveFunctions;

import Components.Dimension;
import Components.Instance;
import Tools.EuclideanProjection;
import Tools.Matrix;

public class Eff_Sphere implements Task{
	
	private int eff_size;
	private Dimension dim; //dimension
	private double opt[];  //the center of sphere
	
	public Eff_Sphere(int eff_s, int size){
		dim = new Dimension();
		dim.setSize(size);
		dim.setDimension(-1, 1, true);
		opt = new double[size];
		for(int i=0; i<size; i++){
			opt[i] = 0.2;
		}
		eff_size = eff_s;
	}

	@Override
	public double getValue(double[] x) {
		// TODO Auto-generated method stub
		double sum = 0;
        double v=0;
          
        //efficient dimensions
        for(int i=0; i<eff_size; i++){//calculate sphere value
            v=x[i]-opt[i];
            sum += v*v;
        }
        
        //non-efficient dimensions
        for(int i=eff_size; i<dim.getSize(); i++){
        	v=x[i]-opt[i];
        	sum += 1.0/(dim.getSize()-eff_size)*v*v;
        }
        
        return sum;
	}

	@Override
	public Dimension getDim() {
		// TODO Auto-generated method stub
		return dim;
	}
	
	

}
