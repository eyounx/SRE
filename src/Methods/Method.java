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
 * Abstract class Method
 * @author Hu Yi-Qi
 * @time 2016.4.24
 * Sub-optimization method in SRE framework should implement this interface
 */
package Methods;

import Components.*;
import ObjectiveFunctions.Task;
import Tools.EuclideanProjection;
import Tools.Matrix;

public abstract class Method {
	
	protected double punishing;
	
	/**
	 * this function is used to map sample in low-dimension space back to high-dimension space, and ensure that high-dimension sample is in original sample space
	 * using Euclidean projection
	 * @param ins: sample in low-dimension space, y
	 * @param sub_dim: low-dimension information
	 * @param ori_dim: high-dimension information
	 * @param proj_matrix: projection matrix A
	 * @param bias: x = A*y+bias
	 * @return
	 */
	public double[] MappingBack(Instance ins, Dimension sub_dim, Dimension ori_dim, double[][] proj_matrix, double[] bias){
		double[] y = new double[sub_dim.getSize()-1];
		for(int i=0; i<sub_dim.getSize()-1; i++){
			y[i] = ins.getFeature(i);
		}
		
		double[] ori_x = Matrix.Mul(proj_matrix, y);
		ori_x = Matrix.Add(ori_x, Matrix.ScalMul(ins.getFeature(sub_dim.getSize()-1), bias));
		
		double[] map_x = EuclideanProjection.Mapping(ori_dim, ori_x);
		
		double sum = 0;
		for(int i=0; i<ori_dim.getSize(); i++){
			sum += Math.abs(ori_x[i] - map_x[i]);
		}
		punishing = sum;
		return map_x;
		
	}
	
	/**
	 * the optimization algorithm used in SRE should override this function
	 * @return the best solution found by algorithm
	 */
	public abstract Instance Optimization(Dimension sub_dim, Dimension ori_dim, Task t, double[][] proj_matrix, double[] bias);

}
