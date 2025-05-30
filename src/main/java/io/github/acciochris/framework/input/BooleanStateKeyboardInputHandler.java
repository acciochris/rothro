/*
 * Copyright (c) 2010-2022 William Bittle  http://www.dyn4j.org/
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice, this list of conditions 
 *     and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 *     and the following disclaimer in the documentation and/or other materials provided with the 
 *     distribution.
 *   * Neither the name of dyn4j nor the names of its contributors may be used to endorse or 
 *     promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR 
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER 
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package io.github.acciochris.framework.input;

import java.awt.Component;

public class BooleanStateKeyboardInputHandler extends AbstractKeyboardInputHandler {
	/** If the key state is active (pressed) */
	private boolean active;
	
	/** True if the active state has been handled */
	private boolean hasBeenHandled;
	
	public BooleanStateKeyboardInputHandler(Component component, Key... keys) {
		super(component, keys);
		this.active = false;
		this.hasBeenHandled = false;
	}
	
	public BooleanStateKeyboardInputHandler(Component component, int... keys) {
		super(component, keys);
		this.active = false;
		this.hasBeenHandled = false;
	}

	@Override
	protected void onKeyPressed() {
		super.onKeyPressed();
		
		// save the old state
		boolean active = this.active;
		
		// set the state to active
		this.active = true;
		
		// if the state transitioned from inactive to active
		// flag that it needs to be handled
		if (!active) {
			this.hasBeenHandled = false;
		}
	}
	
	@Override
	protected void onKeyReleased() {
		super.onKeyReleased();
		this.active = false;
	}
	
	@Override
	public boolean isActive() {
		return this.active;
	}
	
	public boolean isActiveButNotHandled() {
		if (this.hasBeenHandled)
			return false;
		
		return this.active;
	}
	
	public void setHasBeenHandled(boolean hasBeenHandled) {
		this.hasBeenHandled = hasBeenHandled;
	}
}
