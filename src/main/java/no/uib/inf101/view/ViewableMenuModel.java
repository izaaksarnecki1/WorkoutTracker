package no.uib.inf101.view;

import javax.swing.*;

public interface ViewableMenuModel {
  default JButton[] getButtonsOnScreen(){return null;}
}
