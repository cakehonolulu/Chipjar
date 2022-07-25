package chipjar;

public class Cpu {
	private final byte REGISTER_SIZE = 16;
	private final byte STACK_SIZE = 16;
	private final short INITIAL_PROGRAM_COUNTER = 0x200;
	
	protected byte m_registers[];
	
	protected byte m_timer_delay;
	
	protected byte m_sound_delay;
	
	protected short m_index_register;
	
	protected short m_program_counter;
	
	protected short m_stack[];
	
	protected byte m_stack_pointer;

	protected Cpu() {
		this.m_registers = new byte[REGISTER_SIZE];
		this.m_timer_delay = 0;
		this.m_sound_delay = 0;
		this.m_index_register = 0;
		this.m_program_counter = INITIAL_PROGRAM_COUNTER;
		this.m_stack = new short[STACK_SIZE];
		this.m_stack_pointer = 0;
	}
}
