package hu.bme.mit.spaceship;

/**
* A simple spaceship with two proton torpedo stores and four lasers
*/
public class GT4500 implements SpaceShip {

  public TorpedoStore primaryTorpedoStore;
  public TorpedoStore secondaryTorpedoStore;

  private boolean wasPrimaryFiredLast = false;

  public GT4500(TorpedoStore torpedoStore, TorpedoStore torpedoStore2) {
    this.primaryTorpedoStore = torpedoStore;
    this.secondaryTorpedoStore = torpedoStore2;
  }

  public boolean fireLaser(FiringMode firingMode) {
    // TODO not implemented yet
    return false;
  }

  public boolean primaryFireAction() {
    if (! secondaryTorpedoStore.isEmpty()) {
      wasPrimaryFiredLast = false;
      return secondaryTorpedoStore.fire(1);

    }
    else if (! primaryTorpedoStore.isEmpty()) {
      wasPrimaryFiredLast = true;
      return primaryTorpedoStore.fire(1);
    }
    return false;
  }

  public boolean secondaryFireAction() {
    if (! primaryTorpedoStore.isEmpty()) {
      wasPrimaryFiredLast = true;
      return primaryTorpedoStore.fire(1);
    }
    else if (! secondaryTorpedoStore.isEmpty()) {
      wasPrimaryFiredLast = false;
      return secondaryTorpedoStore.fire(1);
    }
    return false;
  }

  public boolean singleFireAction() {
    if (wasPrimaryFiredLast)
      return primaryFireAction();
    return secondaryFireAction();
  }

  public boolean allFireAction() {
    boolean firingSuccess = false;
    if (!primaryTorpedoStore.isEmpty() && !secondaryTorpedoStore.isEmpty()) {
      firingSuccess = primaryTorpedoStore.fire(1);
      if (firingSuccess)
        firingSuccess = secondaryTorpedoStore.fire(1);
    }
    return firingSuccess;
  }


  /**
  * Tries to fire the torpedo stores of the ship.
  *
  * @param firingMode how many torpedo bays to fire
  * 	SINGLE: fires only one of the bays.
  * 			- For the first time the primary store is fired.
  * 			- To give some cooling time to the torpedo stores, torpedo stores are fired alternating.
  * 			- But if the store next in line is empty, the ship tries to fire the other store.
  * 			- If the fired store reports a failure, the ship does not try to fire the other one.
  * 	ALL:	tries to fire both of the torpedo stores.
  *
  * @return whether at least one torpedo was fired successfully
  */
  @Override
  public boolean fireTorpedo(FiringMode firingMode) {

    boolean firingSuccess = false;

    if (firingMode == FiringMode.SINGLE)
      firingSuccess = singleFireAction();
    else if (firingMode == FiringMode.ALL)
      firingSuccess = allFireAction();

    return firingSuccess;
  }

}
