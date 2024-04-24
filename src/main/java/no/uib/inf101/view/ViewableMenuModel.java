package no.uib.inf101.view;

import java.util.Map;

// Use to get user data from model to view.
public interface ViewableMenuModel {
  Map<String, String> getUserProfile();
}
