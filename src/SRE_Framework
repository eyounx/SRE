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
 * Sequential Random Embedding (SRE) framework
 * @author Hu Yi-QI
 * @time 2016.4.24
 */
package SRE_Framework;

import Components.*;
import Methods.*;
import ObjectiveFunctions.*;
import Tools.EuclideanProjection;
import Tools.Matrix;
import Tools.RandomOperator;

public class SRE_Opt {
	
	private Task obj_func;            //objective function i.e. task
	private Method opt_method;        //optimization method used in SRE
	private int sub_dim_size;         //dimension information of sub-optimization problem
	private Dimension ori_dimension;  //dimension information of original optimization problem
	private int step_size;            //number of sequential random embeddings
	
	private double variance;          //variance of Gaussian distribution used to generate projection matrix
	private double[][] sub_region;    //sub-optimization space
	
	private Instance optimal;         //best solution recently
	
	private RandomOperator ro;
	
	public SRE_Opt(){
		ro = new RandomOperator();
	}
	
	
	/**
	 * set task (objective function)
	 * @param t task
	 */
	public void setTask(Task t){
		this.obj_func = t;
	}
	
	/**
	 * set optimization method used in SRE
	 * @param met method
	 */
	public void setMethod(Method met){
		this.opt_method = met;
	}
	
	/**
	 * set sub-dimension
	 * @param dim
	 */
	public void setSubDimensionSize(int size){
		this.sub_dim_size = size;
	}
	
	/**
	 * set original dimension
	 * @param dim
	 */
	public void setOriDiemnsion(Dimension dim){
		this.ori_dimension = dim;
	}
	
	/**
	 * set variance
	 * @param var
	 */
	public void setVariance(double var){
		this.variance = var;
	}
	
	/**
	 * set number of sequential random embeddings
	 * @param size
	 */
	public void setStepSize(int size){
		this.step_size = size;
	}
	
	/**
	 * set sub-optimization space
	 * @param region
	 */
	public void setSubRegion(double[][] region){
		this.sub_region = region;
	}
	
	/**
	 * set all parameters in SRE
	 * @param t
	 * @param met
	 * @param sub
	 * @param ori
	 * @param var
	 * @param step
	 */
	public void setParameters(Task t, Method met, int sub, Dimension ori, double var, int step, double[][] reg){
		setTask(t);
		setMethod(met);
		setSubDimensionSize(sub);
		setOriDiemnsion(ori);
		setVariance(var);
		setStepSize(step);
		setSubRegion(reg);
		return ;
	}
	
	public Instance getOptimal(){
		return optimal;
	}
	
	/**
	 * generate sub-dimension for each optimization in SRE
	 * @param size dimension size
	 * @param region feasible region
	 * @param type
	 * @return
	 */
	protected Dimension GenerateSubDimension(int size, double[][] region, boolean type){
		Dimension sub_dim = new Dimension();
		sub_dim.setSize(size+1);
		for(int i=0; i<size; i++){
			sub_dim.setDimension(i, region[i][0], region[i][1], type);
		}
		sub_dim.setDimension(size, -1, 1, true);        //scale
		return sub_dim;
	}
	
	/**
	 * generate projection matrix
	 * @param sub_dim specify sub-dimension size (column number of matrix)
	 * @param ori_dim specify original dimension size (line number of matrix)
	 * @param var the variance parameter
	 * @return
	 */
	protected double[][] GenerateProjectionMatrix(int sub_size, int ori_size, double var){
		double[][] matrix = new double[ori_size][sub_size];
		for(int i=0; i<ori_size; i++)
			for(int j=0; j<sub_size; j++)
				matrix[i][j] = ro.getGaussion(0, var);
		return matrix;
	}
	
	/**
	 * after projection x maybe not in original space, mapping x back using Euclidean projection
	 * @param x
	 * @param ori_dim
	 * @return
	 */
/*	protected double[] Mapping(double[] x, Dimension ori_dim){
		double[] region;
		for(int i=0; i<x.length; i++){
			region = ori_dim.getRegion(i);
			if(x[i]>=region[0]&&x[i]<=region[1])
				;
			else{
				if(x[i]<region[0])
					x[i] = region[0];
				else
					x[i] = region[1];
			}	
		}
		return x;
	}*/
	
	public void SRE_Optimization(){
		
		double[] y = new double[sub_dim_size];
		double[][] projection;          //projection matrix
		Dimension sub_dim;
		sub_dim = GenerateSubDimension(sub_dim_size, sub_region, true);
		
		Instance temp_optimal;
		
		//initialize bias
		double[] bias = new double[ori_dimension.getSize()];
		for(int i=0; i<ori_dimension.getSize(); i++)
			bias[i] = 0;
		
		//loop of random embeddings
		for(int step=0; step<step_size; step++){
			
			projection = GenerateProjectionMatrix(sub_dim_size, ori_dimension.getSize(), variance);
			temp_optimal = opt_method.Optimization(sub_dim, ori_dimension, obj_func, projection, bias);
			
			System.out.println("temp optimal:" + temp_optimal.getValue());
			
			for(int i=0; i<sub_dim_size; i++){
				y[i] = temp_optimal.getFeature(i);
			}
			//update optimal and bias
			if(step == 0){
				optimal = new Instance(ori_dimension);
				optimal.setFeatures(EuclideanProjection.Mapping(ori_dimension, Matrix.Mul(projection, y)));
				optimal.setValue(temp_optimal.getValue());
				bias = optimal.getFeature();
			}else{
				if(temp_optimal.getValue()<optimal.getValue()){
					optimal.setFeatures(EuclideanProjection.Mapping(ori_dimension, Matrix.Add(Matrix.Mul(projection, y), Matrix.ScalMul(temp_optimal.getFeature(sub_dim_size), bias))));
					optimal.setValue(temp_optimal.getValue());
					bias = optimal.getFeature();
				}
			}
			
//			System.out.println("step "+step+":"+optimal.getValue());
			
		}
		
		return ;
		
	}
	
	
	
	
	

}
