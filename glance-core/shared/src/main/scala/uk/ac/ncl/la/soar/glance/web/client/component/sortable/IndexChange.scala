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
package uk.ac.ncl.la.soar.glance.web.client.component.sortable

import scala.collection.generic.CanBuildFrom
import scala.collection.mutable.ListBuffer

/**
  * A change in index of an item generated by dragging
  *
  * @param oldIndex The item's old index
  * @param newIndex The item's new index
  */
case class IndexChange(oldIndex: Int, newIndex: Int) {

  import IndexChange._

  def updatedList[A](l: List[A]) = updatedTo[List](l)

  def updatedTo[To[_]] = new UpdateColWrapper[To](oldIndex, newIndex)

}

object IndexChange {

  /** Simple Wrapper class to neaten up the updatedTo api, not strictly necessary */
  final class UpdateColWrapper[To[_]](oldIndex: Int, newIndex: Int) {
    def apply[A](s: Seq[A])(implicit cbf: CanBuildFrom[Nothing, A, To[A]]) = {
      val lb = ListBuffer(s: _*)
      val e = lb.remove(oldIndex)
      lb.insert(newIndex, e)
      lb.to[To]
    }
  }
}
