package HW1.a_star;

/**
 * statuses the A* tiles can have
 */
public enum TileStatus
{
    WALL,
    TO_EXPLORE,
    EMPTY,
    EXPLORED,
    PATH,
    UNFAVORABLE
}
