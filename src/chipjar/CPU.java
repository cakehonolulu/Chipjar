package chipjar;

public class CPU {
	private final byte REGISTER_SIZE = 16;
	private final byte STACK_SIZE = 16;
	
	byte m_registers[];
	
	byte m_timer_delay;
	
	byte m_sound_delay;
	
	short m_index_register;
	
	short m_program_counter;
	
	short m_stack[];
	
	byte m_stack_pointer;
}
