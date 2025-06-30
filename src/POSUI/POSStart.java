package POSUI;

import POSPD.Store;
import POSDM.*;
//import POSDM.StoreDM.*;

public class POSStart {

	public static void main(String[] args) {
		Store myStore;
		myStore = new Store();
		StoreDM.loadStore(myStore);
		POSJFRAME.open(myStore);
	
	}
}
