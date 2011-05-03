package com.cloudbees.eclipse.dev.ui.views.build;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.cloudbees.eclipse.core.forge.api.ForgeSync;
import com.cloudbees.eclipse.dev.ui.CBImages;
import com.cloudbees.eclipse.dev.ui.CloudBeesDevUiPlugin;

public class ArtifactsLabelProvider extends LabelProvider {

  public ArtifactsLabelProvider() {
    super();
  }

  @Override
  public String getText(final Object element) {
    if (element instanceof ForgeSync.ArtifactPathItem) {
      String path = ((ForgeSync.ArtifactPathItem) element).item.relativePath;
      return path;
    }

    return super.getText(element);
  }

  @Override
  public Image getImage(final Object element) {
    if (element instanceof ForgeSync.ArtifactPathItem) {
      if (((ForgeSync.ArtifactPathItem) element).item.relativePath.endsWith(".war")) {
        return CloudBeesDevUiPlugin.getImage(CBImages.IMG_DEPLOY);
      }
      return CloudBeesDevUiPlugin.getImage(CBImages.IMG_FILE);
    }

    return super.getImage(element);
  }

  @Override
  public void dispose() {
    super.dispose();
  }

}
