package com.monyetmabuk.rajawali.tutorials.examples.animation;

import rajawali.animation.mesh.SkeletalAnimationObject3D;
import rajawali.animation.mesh.SkeletalAnimationSequence;
import rajawali.lights.DirectionalLight;
import rajawali.parser.ALoader.ParsingException;
import rajawali.parser.md5.LoaderMD5Anim;
import rajawali.parser.md5.LoaderMD5Mesh;
import android.content.Context;

import com.monyetmabuk.rajawali.tutorials.R;
import com.monyetmabuk.rajawali.tutorials.examples.AExampleFragment;

public class SkeletalAnimationMD5Fragment extends AExampleFragment {

	@Override
	protected AExampleRenderer createRenderer() {
		return new SkeletalAnimationMD5Renderer(getActivity());
	}

	private final class SkeletalAnimationMD5Renderer extends AExampleRenderer {
		private DirectionalLight mLight;
		private SkeletalAnimationObject3D mObject;

		public SkeletalAnimationMD5Renderer(Context context) {
			super(context);
		}

		protected void initScene() {
			mLight = new DirectionalLight(1f, -0.2f, -1.0f); // set the direction
			mLight.setColor(1.0f, 1.0f, 1.0f);
			mLight.setPower(2);

			getCurrentScene().addLight(mLight);
			getCurrentCamera().setY(1);
			getCurrentCamera().setZ(6);

			try {
				LoaderMD5Mesh meshParser = new LoaderMD5Mesh(this,
						R.raw.boblampclean_mesh);
				meshParser.parse();

				LoaderMD5Anim animParser = new LoaderMD5Anim("attack2", this,
						R.raw.boblampclean_anim);
				animParser.parse();

				SkeletalAnimationSequence sequence = (SkeletalAnimationSequence) animParser
						.getParsedAnimationSequence();

				mObject = (SkeletalAnimationObject3D) meshParser
						.getParsedAnimationObject();
				mObject.setAnimationSequence(sequence);
				mObject.setScale(.04f);
				mObject.setRotY(180);
				mObject.play();

				addChild(mObject);
			} catch (ParsingException e) {
				e.printStackTrace();
			}
		}

	}
}
