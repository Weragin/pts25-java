package sk.uniba.fmph.dcs.terra_futura;

import org.junit.Test;
import org.mockito.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MoveCardTest {

    @Test
    public void testShouldMoveWhenCan() {
        Pile pile = mock(Pile.class);
        Grid grid = mock(Grid.class);
        Card card = mock(Card.class);
        GridPosition pos = mock(GridPosition.class);

        when(grid.canPutCard(pos)).thenReturn(true);
        when(pile.getCard(1)).thenReturn(card);

        boolean result = MoveCard.moveCard(1, pile, pos, grid);

        assertTrue(result);

        verify(grid).putCard(pos, card);
        verify(pile).takeCard(1);
    }

    @Test
    public void testDontMoveWhenCant() {
        Pile pile = mock(Pile.class);
        Grid grid = mock(Grid.class);
        Card card = mock(Card.class);
        GridPosition pos = mock(GridPosition.class);

        when(grid.canPutCard(pos)).thenReturn(false);
        when(pile.getCard(1)).thenReturn(card);

        boolean result = MoveCard.moveCard(1, pile, pos, grid);

        assertFalse(result);

        verify(grid, never()).putCard(pos, card);
        verify(pile, never()).takeCard(1);
    }

}
