/** soar
  *
  * Copyright (c) 2017 Hugo Firth
  * Email: <me@hugofirth.com/>
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at:
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package uk.ac.ncl.la.soar.glance.eval

import java.util.UUID

import cats._
import cats.implicits._
import cats.data.NonEmptyVector
import io.circe._
import io.circe.syntax._
import uk.ac.ncl.la.soar.ModuleCode

/**
  * Basic struct containing series of surveys
  */
case class Collection(id: UUID,
                      module: ModuleCode,
                      surveyIds: NonEmptyVector[UUID],
                      currentIdx: Int = 0) {

  def current: UUID = surveyIds.get(currentIdx).getOrElse(surveyIds.head)

  def currentIsLast: Boolean = (surveyIds.length - 1) == currentIdx

  val numEntries: Int = surveyIds.length

  def isValidIdx(idx: Int) = idx >= 0 && idx < numEntries
}
