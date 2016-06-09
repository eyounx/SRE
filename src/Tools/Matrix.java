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
 * Class Matrix
 * @author Yi-Qi Hu
 * @time 2016.4.26
 * This class implements some operations to deal with matrix calculation
 */

package Tools;

public class Matrix {
	
	/**
	 * matrix multiplication
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[][] Mul(double[][] a, double b[][]){
		if(a[0].length!=b.length)
			return null;
		double[][] result = new double[a.length][b[0].length];
		double temp;
		for(int i=0; i<a.length; i++){
			for(int j=0; j<b[0].length; j++){
				temp = 0;
				for(int k=0; k<a[0].length; k++){
					temp += a[i][k]*b[k][j];
				}
				result[i][j] = temp;
			}
		}
		return result;
	}

	/**
	 * matrix multiplication
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[] Mul(double[][] a, double b[]){
		if(a[0].length!=b.length)
			return null;
		double[] result = new double[a.length];
		double temp;
		for(int i=0; i<a.length; i++){
			temp = 0;
			for(int k=0; k<a[0].length; k++){
				temp += a[i][k]*b[k];
			}
			result[i] = temp;
		}
		return result;
	}
	
	/**
	 * matrix scalar multiplication
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[] ScalMul(double a, double[] b){
		double[] result = new double[b.length];
		for(int i=0; i<b.length; i++){
			result[i] = a*b[i];
		}
		return result;
	}
	
	/**
	 * matrix addition
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[] Add(double[] a, double[] b){
		if(a.length!=b.length){
			return null;
		}
		double[] result = new double[a.length];
		for(int i=0; i<a.length; i++){
			result[i] = a[i]+b[i];
		}
		return result;
	}
	
	/**
	 * matrix subtraction
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[] Sub(double[] a, double[] b){
		if(a.length!=b.length)
			return null;
		double[] result = new double[a.length];
		for(int i=0; i<a.length; i++){
			result[i] = a[i] - b[i];
		}
		return result;
	}

}
