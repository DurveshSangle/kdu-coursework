import { RefObject, useRef } from 'react';

export function ScrollToTop() {
  const ref1 = useRef<HTMLParagraphElement>(null);

  const handleScroll = (ref: RefObject<HTMLParagraphElement>) => {
    if (!ref.current) return;
    window.scrollTo({
      top: ref.current.offsetTop,
      left: 0,
      behavior: "smooth",
    });
  };

  return (
    <div>
      <p ref={ref1}>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque et amet aut sit culpa perspiciatis aperiam ut libero laboriosam. Non illum cupiditate qui vitae dolorem corporis explicabo, rem nisi quibusdam?</p>
      <button onClick={()=> handleScroll(ref1)} style={{position:"fixed", right:"10px", top:"10px"}}>
        Scroll To Top
      </button>
    </div>
    
  );
}
