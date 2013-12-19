/*
 * Copyright (c) 2013. Regents of the University of California
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.berkeley.cs.amplab.avocado.calls.pileup

import edu.berkeley.cs.amplab.avocado.calls.VariantCall
import edu.berkeley.cs.amplab.avocado.Avocado
import edu.berkeley.cs.amplab.adam.models.{ADAMRod, ADAMVariantContext}
import org.apache.spark.{SparkContext, Logging}
import org.apache.spark.rdd.RDD

/**
 * Abstract class for calling variants on reads. 
 */
abstract class PileupCall extends VariantCall {

  val callName: String
  val coverage = Avocado.coverage
  val reducePartitions = Avocado.reducePartitions

  /**
   * Method signature for variant calling operation.
   *
   * @param[in] pileups An RDD of pileups.
   * @return An RDD containing called variants.
   */
  def call (pileups: RDD [ADAMRod]): RDD [ADAMVariantContext]

  override def isReadCall (): Boolean = false
  override def isPileupCall (): Boolean = true
}
