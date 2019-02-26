package puyo

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  import Renderer._
  def render(board: Board): Unit = {
    gc.fill = Color.DarkGreen
    gc.fillRect(0, 0, Main.canvasWidth, Main.canvasHeight)

    for (boba <- board.bobas) {
      drawBoba(boba)
    }
    drawBoba(board.current.p1)
  }
    
    def drawBoba(boba: Boba): Unit = {
      boba.color match {
        case PuyoColor.Red => gc.fill = Color.Red
        case PuyoColor.Blue => gc.fill = Color.Blue
        case PuyoColor.Gray => gc.fill = Color.Gray
        case PuyoColor.Yellow => gc.fill = Color.Yellow
        case PuyoColor.Magenta => gc.fill = Color.Magenta
        case PuyoColor.Green => gc.fill = Color.Green

      }
      gc.fillOval(boba.x * CellSize, boba.y * CellSize, Renderer.CellSize, Renderer.CellSize)
    }
    
}

object Renderer {
  val CellSize = 30
}