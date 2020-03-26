package org.camunda.bpm.spring.boot.example.simple;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.complete;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.execute;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.job;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.runtimeService;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.task;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.camunda.bpm.extension.mockito.DelegateExpressions.autoMock;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;

import org.junit.Test;

/**
 * Ensure the sample.bpmn Process is working correctly.
 */
@Deployment(resources = "loanApproval.bpmn")
public class SampleProcessTest  {

  @Test
  public void start_and_finish_process() {
    autoMock("loanApproval.bpmn");

    final ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("loanApproval");

    assertThat(processInstance).isWaitingAt("UserTask_1");

    complete(task());

    assertThat(processInstance).isWaitingAt("ServiceTask_1");
    execute(job());

    assertThat(processInstance).isEnded();
  }
}