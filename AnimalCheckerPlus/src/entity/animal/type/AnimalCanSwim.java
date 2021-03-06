package entity.animal.type;

import entity.animal.instance.Elephant;
import entity.tile.Tile;
import entity.animal.Animal;
import entity.Board;
import controller.GameController;
import exception.GameWinException;
import exception.InvalidActionException;

/**
 * Created by 谢东方xdf on 2017/1/3.
 */
public abstract class AnimalCanSwim extends Animal {

    @Override
    public void act(GameController.Direction direction, boolean simulate) throws InvalidActionException, GameWinException {
        Tile currentTile = Board.getInstance().getTileByAnimal(this);
        Tile nextTile = Board.getInstance().getTileByDirection(currentTile, direction);

        checkBoundary(nextTile);
        checkAttackFromWater(currentTile, nextTile);

        move(currentTile, nextTile, simulate);
        if (!simulate) {
            checkWin(nextTile);
        }
    }

    private void checkAttackFromWater(Tile currentTile, Tile nextTile) throws InvalidActionException {
        if (currentTile.getType() == Tile.TileType.RIVER
                && nextTile.getType() == Tile.TileType.LAND
                && nextTile.getAnimal() instanceof Elephant) {
            throw new InvalidActionException("不能从水里攻击陆地上的象");
        }
    }
}
