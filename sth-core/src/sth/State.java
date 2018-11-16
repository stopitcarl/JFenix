package sth;

import java.io.Serializable;

/**
 * Represents the state of the survey 
 */
public enum State implements Serializable{
	Created, Open, Closed, Finalized
}
