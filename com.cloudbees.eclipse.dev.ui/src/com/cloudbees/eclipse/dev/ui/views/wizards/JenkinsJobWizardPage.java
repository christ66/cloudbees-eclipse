package com.cloudbees.eclipse.dev.ui.views.wizards;

import java.text.MessageFormat;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.cloudbees.eclipse.core.domain.JenkinsInstance;
import com.cloudbees.eclipse.ui.wizard.CBWizardSupport;
import com.cloudbees.eclipse.ui.wizard.NewJenkinsJobComposite;

public class JenkinsJobWizardPage extends WizardPage {

  public static final String NAME = JenkinsJobWizardPage.class.getSimpleName();
  private static final String TITLE = "Create Jenkins Job";
  private static final String DESCRIPTION = "Create new Jenkins job for this project.";
  private static final String JOB_NAME = "Build {0}";

  private final IProject project;
  private NewJenkinsJobComposite jenkinsComposite;

  protected JenkinsJobWizardPage(IProject project) {
    super(NAME);
    setTitle(TITLE);
    setDescription(DESCRIPTION);
    this.project = project;
  }

  @Override
  public void createControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);

    GridLayout layout = new GridLayout(1, true);
    layout.marginHeight = 10;
    layout.marginWidth = 10;
    layout.verticalSpacing = 10;

    container.setLayout(layout);

    GridData data = new GridData();
    data.grabExcessHorizontalSpace = true;
    data.horizontalAlignment = SWT.FILL;

    this.jenkinsComposite = new NewJenkinsJobComposite(container) {

      @Override
      protected JenkinsInstance[] loadJenkinsInstances() {
        try {
          return CBWizardSupport.getJenkinsInstances(getContainer());
        } catch (Exception e) {
          e.printStackTrace();
          return new JenkinsInstance[] {};
        }
      }

      @Override
      protected void createComponents() {
        createInstanceChooser();
        createJobText();
        addJenkinsInstancesToUI();
      }

      @Override
      protected void validate() {
        if (getJenkinsInstance() == null) {
          updateErrorStatus("Please select Jenkins instance.");
          return;
        }

        if (getJobNameText() == null || getJobNameText().length() == 0) {
          updateErrorStatus("Please specify Jenkins job name.");
          return;
        }

        updateErrorStatus(null);
      }

    };

    this.jenkinsComposite.setLayoutData(data);

    String jobName = MessageFormat.format(JOB_NAME, this.project.getName());
    this.jenkinsComposite.setJobNameText(jobName);

    setControl(container);
  }

  public void updateErrorStatus(String message) {
    setErrorMessage(message);
    setPageComplete(message == null);
  }

  public JenkinsInstance getJenkinsInstance() {
    return this.jenkinsComposite.getJenkinsInstance();
  }

  public String getJobName() {
    return this.jenkinsComposite.getJobNameText();
  }
}
