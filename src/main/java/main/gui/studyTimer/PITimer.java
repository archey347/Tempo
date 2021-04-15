package main.gui.studyTimer;

enum TimerState
{
    Work,
    Rest,
}

/**
 * Holds work / rest times, frequency of big breaks.
 * Handles incrementing time & switching from rest / work times
 */
public class PITimer
{
    private int workSeconds;
    private int restSeconds;
    private int elapsedSeconds;
    private int targetSeconds;
    private TimerState currentState;
    private boolean isPaused;
    
    public PITimer(int workSeconds, int restSeconds)
    {
        this.workSeconds = workSeconds;
        this.restSeconds = restSeconds;
        
        setCurrentState(TimerState.Work);
        targetSeconds = workSeconds;
    }
    
    /**
     * Step the timer by one second.
     */
    public void stepTime()
    {
        // Doesn't step if the timer is paused
        if (isPaused)
        {
            return;
        }
        
        // Checking for state changes
        if (++elapsedSeconds >= targetSeconds)
        {
            if (currentState == TimerState.Work)
            {
                // record session as soon as we get Connor's code
                targetSeconds = restSeconds;
                elapsedSeconds = 0;
                setCurrentState(TimerState.Rest);
            }
            else if (currentState == TimerState.Rest)
            {
                targetSeconds = workSeconds;
                elapsedSeconds = 0;
                setCurrentState(TimerState.Work);
            }
        }
    }
    
    public void togglePause()
    {
        isPaused = !isPaused;
    }
    
    public boolean getPaused()
    {
        return isPaused;
    }

    public void setElapsedSeconds(int elapsedSeconds) {
        this.elapsedSeconds = elapsedSeconds;
    }

    public int getElapsedSeconds() {
        return elapsedSeconds;
    }

    /**
     * Gets the current time remaining as a formatted string.
     *
     * @return The time remaining.
     */
    public String getTimeString()
    {
        int minutes = getSecondsRemaining() / 60;
        int seconds = getSecondsRemaining() - minutes * 60;
        int hours = minutes / 60;
        minutes -= hours * 60;
        
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    
    /**
     * Calculates the time remaining before switching.
     *
     * @return The time remaining.
     */
    public int getSecondsRemaining()
    {
        return targetSeconds - elapsedSeconds;
    }
    
    /**
     * Extends the timer by a given duration.
     *
     * @param seconds The number of seconds to add.
     */
    public void extendWorkDuration(int seconds)
    {
        targetSeconds += seconds;
    }

    /**
     *
     * @return in minutes the work time
     */
    public int getWorkMins()
    {
        return workSeconds / 60;
    }
    
    public void setWorkSeconds(int workSeconds)
    {
        this.workSeconds = workSeconds;
        
        if (currentState == TimerState.Work && getSecondsRemaining() > workSeconds)
        {
            targetSeconds = workSeconds;
        }
    }
    
    public int getRestMins()
    {
        return restSeconds / 60;
    }
    
    public void setRestSeconds(int restSeconds)
    {
        this.restSeconds = restSeconds;
        
        if (currentState == TimerState.Rest && getSecondsRemaining() > restSeconds)
        {
            targetSeconds = restSeconds;
        }
    }
    
    public TimerState getCurrentState()
    {
        return currentState;
    }
    
    private void setCurrentState(TimerState newState)
    {
        this.currentState = newState;
    }
}
