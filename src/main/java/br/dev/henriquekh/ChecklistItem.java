package br.dev.henriquekh;

public class ChecklistItem {
  private boolean done;
  private String description;

  public ChecklistItem(boolean done, String description) {
    setDone(done);
    setDescription(description);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean getDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  @Override
  public String toString() {
    return "[" + (getDone() ? "x" : " ") + "] - " + getDescription();
  }
}
