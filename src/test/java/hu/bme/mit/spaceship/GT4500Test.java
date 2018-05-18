package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTS1;
  private TorpedoStore mockTS2;

  @Before
  public void init(){
    mockTS1 = mock(TorpedoStore.class);
    mockTS2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTS1, mockTS2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    when(ship.fireTorpedo(FiringMode.SINGLE)).thenReturn(true);
  }

  @Test
  public void fireTorpedo_All_Success(){
    when(ship.fireTorpedo(FiringMode.ALL)).thenReturn(true);
  }

}
