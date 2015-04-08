package sevenStateSimulation;

/**
 * 
 * @author Gabriel
 *
 */
public class ProcessControlBlock {
        private int processID,          // Identifier del proceso.
        parentProcessID,                        // Identifier del proceso madre.
        userID,                                         // Identifier del usuario responsable.
        programCounter,                         // Address de donde se está ejecutando.
        pageInMemory;                           // Lugar en memoria donde reside el programa.
 
        private ProcessStatusType processStatus;
 
        public ProcessControlBlock(int a, int b, int c) { // Constructor function
                this.processID  =  a;
                this.parentProcessID = b;
                this.userID =  c;
                this.processStatus = ProcessStatusType.NEW;
        } // final del constructor
 
        public void setProgramCounter( int pc ) {
                this.programCounter = pc;
                setPageInMemory();
                return;
        } // setProgramCounter
 
        public void  admit(int address) throws Exception {
                if ( this.processStatus == ProcessStatusType.NEW) {
                        this.processStatus = ProcessStatusType.READY;
                        setProgramCounter(address);
                        return;
                }else
                      throw new Exception("Error in admit( )" );
        } // admit
        
        public void admitReadySuspend(int address) throws Exception{
        	if ( this.processStatus == ProcessStatusType.NEW) {
                this.processStatus = ProcessStatusType.READYSUSPEND;
                setProgramCounter(address);
                return;
        	}else
                throw new Exception("Error in admitReadySuspend()" );
        }
        
        public void activate() throws Exception{
        	if(this.processStatus == ProcessStatusType.READYSUSPEND){
        		this.processStatus = ProcessStatusType.READY;
        		return;
        	}else
        		throw new Exception("Error in activate()");
        }
 
        public void  timeOut() throws Exception {
                if ( this.processStatus == ProcessStatusType.RUNNING ) {
                        this.processStatus = ProcessStatusType.READY;
                        setProgramCounter(this.programCounter + 6);
                        return;
                }
                else
                        throw new Exception("Error in timeOut( )" );
        } // fin de timeout()
 
 
        public void eventWait() throws Exception {
                if ( this.processStatus == ProcessStatusType.RUNNING ) {
                        this.processStatus = ProcessStatusType.BLOCKED;
                        return;    
                }
                else
                        throw new Exception("Error in eventWait( )" );
        } // eventWait
 
 
        public void kill() {
                this.processStatus = ProcessStatusType.EXIT;
                return;
        } // kill
 
        public void suspend() throws Exception {
        	if(this.processStatus == ProcessStatusType.BLOCKED){
        		this.processStatus = ProcessStatusType.BLOCKEDSUSPEND;
        		return;
        	}else if(this.processStatus == ProcessStatusType.RUNNING){
        		this.processStatus = ProcessStatusType.READYSUSPEND;
        		setProgramCounter(this.programCounter + 6);
        		return;
        	}else if(processStatus == ProcessStatusType.READY){
        		this.processStatus = ProcessStatusType.READYSUSPEND;
        		return;
        	}else
        		throw new Exception("Error in suspend()");
        }
        
        public void setPageInMemory() {
                this.pageInMemory = this.programCounter / 1000;
                return;
        } // setPageInMemory
 
 
        public void  dispatch() throws Exception {
                if ( this.processStatus == ProcessStatusType.READY ) {
                        this.processStatus = ProcessStatusType.RUNNING;
                        return;
                }
                else
                        throw new Exception("Error in dispatch( )" );
        } // dispatch
 
 
        public void  release() throws Exception {
                if ( this.processStatus == ProcessStatusType.RUNNING)   {
                        this.processStatus = ProcessStatusType.EXIT;
                        return;    
                }
                else
                        throw new Exception("Error in release( )" );
        } // release
 
       
        public void  eventOccurs() throws Exception {
                if ( this.processStatus == ProcessStatusType.BLOCKED ) {
                        this.processStatus = ProcessStatusType.READY;
                        return;      
                }else if(this.processStatus == ProcessStatusType.BLOCKEDSUSPEND){
                		this.processStatus = ProcessStatusType.READYSUSPEND;
                		return;
                }else
                     	throw new Exception("Error in eventOccurs( )" );
        } // eventOccurs
        
        
		public int getProcessID() {
			return processID;
		}

		public void setProcessID(int processID) {
			this.processID = processID;
		}

		public int getParentProcessID() {
			return parentProcessID;
		}

		public void setParentProcessID(int parentProcessID) {
			this.parentProcessID = parentProcessID;
		}

		public int getUserID() {
			return userID;
		}

		public void setUserID(int userID) {
			this.userID = userID;
		}

		public int getPageInMemory() {
			return pageInMemory;
		}

		public void setPageInMemory(int pageInMemory) {
			this.pageInMemory = pageInMemory;
		}

		public ProcessStatusType getProcessStatus() {
			return processStatus;
		}

		public void setProcessStatus(ProcessStatusType processStatus) {
			this.processStatus = processStatus;
		}

		@Override
		public String toString() {
			return "ProcessControlBlock [processID=" + processID
					+ ", parentProcessID=" + parentProcessID + ", userID="
					+ userID + ", programCounter=" + programCounter
					+ ", pageInMemory=" + pageInMemory + ", processStatus="
					+ processStatus + "]";
		}
       
        
 
} // final de la class