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

  @Test
  public void fireTorpedo_PrimaryFire_Success(){
    when(ship.primaryFireAction()).thenReturn(true);
  }

  @Test
  public void fireTorpedo_SecondaryFire_Success(){
    when(ship.secondaryFireAction()).thenReturn(true);
  }

  @Test
  public void fireTorpedo_PrimaryFire_Fail(){
    ship.primaryTorpedoStore.makeEmpty();
    ship.secondaryTorpedoStore.makeEmpty();
    when(ship.primaryFireAction()).thenReturn(false);
  }

  @Test
  public void fireTorpedo_SecondaryFire_Fail(){
    ship.primaryTorpedoStore.makeEmpty();
    ship.secondaryTorpedoStore.makeEmpty();
    when(ship.secondaryFireAction()).thenReturn(false);
  }

  @Test
  public void fireTorpedo_AllFire_Success(){
    when(ship.allFireAction()).thenReturn(true);
  }

  @Test
  public void fireTorpedo_AllFire_Fail(){
    ship.primaryTorpedoStore.makeEmpty();
    ship.secondaryTorpedoStore.makeEmpty();
    when(ship.allFireAction()).thenReturn(false);
  }

}
