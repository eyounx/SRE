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
 * Interface Task
 * @author Yi-Qi Hu
 * @time 2016.4.24
 * this interface provide the same name with different objective function for optimizer. Optimizer can obtain the objective function conveniently
 */
package ObjectiveFunctions;

import Components.*;

public interface Task {
	
	//for calculating objective function
	public abstract double getValue(double[] x);
	
	//for get dimension message for each task
	public abstract Dimension getDim();

}
